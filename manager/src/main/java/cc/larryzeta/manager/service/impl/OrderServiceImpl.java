package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.api.order.model.OrderDTO;
import cc.larryzeta.manager.biz.AccountBiz;
import cc.larryzeta.manager.biz.UserBiz;
import cc.larryzeta.manager.dao.OrderRecordDAO;
import cc.larryzeta.manager.dao.XrayAccountInfoDAO;
import cc.larryzeta.manager.entity.TOrderRecord;
import cc.larryzeta.manager.entity.TXrayAccountInfo;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.enumeration.StatusEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.service.OrderService;
import cc.larryzeta.manager.service.XrayService;
import cc.larryzeta.manager.util.JsonUtils;
import cc.larryzeta.manager.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private AccountBiz accountBiz;

    @Autowired
    private XrayAccountInfoDAO xrayAccountInfoDAO;

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private OrderRecordDAO orderRecordDAO;

    @Autowired
    private XrayService xrayService;

    @Override
    public List<OrderDTO> getOrders(OrderDTO orderDTO) {

        log.info("getOrders service START orderDTO: [{}]", JsonUtils.toJSONString(orderDTO));

        TOrderRecord query = null;
        if (orderDTO != null) {
            query = new TOrderRecord();
            BeanUtils.copyProperties(orderDTO, query);
        }

        List<TOrderRecord> orderRecordList = orderRecordDAO.queryTOrderRecord(query);

        if (orderRecordList == null || orderRecordList.isEmpty()) {
            return Collections.emptyList();
        }

        List<OrderDTO> orderDTOList = new ArrayList<>(orderRecordList.size());

        for (TOrderRecord orderRecord : orderRecordList) {
            orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderRecord, orderDTO);
            orderDTOList.add(orderDTO);
        }

        log.info("getOrders service END orderDTOList: [{}]", JsonUtils.toJSONString(orderDTOList));

        return orderDTOList;
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Integer userId) {

        log.info("getOrdersByUserId service START userId: [{}]", userId);

        userBiz.jwtPermission(userId);

        OrderDTO query = new OrderDTO();
        query.setUserId(userId);

        return getOrders(query);
    }

    @Override
    public OrderDTO getOrderByOrderId(String orderId) {

        log.info("getOrderByOrderId service START orderId: [{}]", orderId);

        TOrderRecord query = new TOrderRecord();
        query.setOrderId(orderId);
        List<TOrderRecord> orderRecordList = orderRecordDAO.queryTOrderRecord(query);

        if (orderRecordList == null || orderRecordList.isEmpty()) {
            log.warn("deleteOrder orderId:[{}] not found", orderId);
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "order not found");
        }

        TOrderRecord orderRecord = orderRecordList.get(0);

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderRecord, orderDTO);

        log.info("getOrderByOrderId service END orderDTO: [{}]", JsonUtils.toJSONString(orderDTO));
        return orderDTO;
    }

    @Override
    public void addOrder(OrderDTO orderDTO) {

        log.info("addOrder service START orderDTO: [{}]", JsonUtils.toJSONString(orderDTO));

        Integer orderDays = Integer.parseInt(orderDTO.getOrderDays());
        Integer orderPrice = Integer.parseInt(orderDTO.getOrderPrice());

        String orderId = UUID.randomUUID().toString();

        userBiz.jwtPermission(orderDTO.getUserId());

        TOrderRecord tOrderRecord = new TOrderRecord();

        tOrderRecord.setOrderId(orderId);
        tOrderRecord.setUserId(orderDTO.getUserId());
        tOrderRecord.setOrderDays(String.valueOf(orderDays));
        tOrderRecord.setOrderPrice(String.valueOf(orderPrice));

        orderRecordDAO.saveTOrderRecord(tOrderRecord);

        log.info("addOrder service END");

    }

    @Override
    public void deleteOrder(String orderId) {

        log.info("deleteOrder orderId:[{}]", orderId);

        TOrderRecord query = new TOrderRecord();
        query.setOrderId(orderId);
        List<TOrderRecord> orderRecordList = orderRecordDAO.queryTOrderRecord(query);

        if (orderRecordList == null || orderRecordList.isEmpty()) {
            log.warn("deleteOrder orderId:[{}] not found", orderId);
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "order not found");
        }

        TOrderRecord orderRecord = orderRecordList.get(0);
        if (StatusEnum.DELETED.code.equals(orderRecord.getOrderStatus())) {
            log.warn("deleteOrder orderId:[{}] has deleted", orderId);
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "has deleted");
        }

        TOrderRecord update = new TOrderRecord();
        update.setId(orderRecord.getId());
        update.setOrderStatus(StatusEnum.DELETED.code);
        orderRecordDAO.updateTOrderRecord(update);

        log.info("deleteOrder service END");

    }

    @Override
    public void activeOrder(String orderId) {

        log.info("activeOrder service START orderId: [{}]", orderId);

        TOrderRecord query = new TOrderRecord();
        query.setOrderId(orderId);
        List<TOrderRecord> orderRecordList = orderRecordDAO.queryTOrderRecord(query);

        if (orderRecordList == null || orderRecordList.isEmpty()) {
            log.warn("activeOrder orderId:[{}] not found", orderId);
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "order not found");
        }

        TOrderRecord orderRecord = orderRecordList.get(0);

        TXrayAccountInfo accountInfo = null;

        try {
            accountInfo = accountBiz.getAccount(orderRecord.getUserId());
        } catch (Exception e) {
            log.info("activeOrder userId:[{}] accountInfo not found", orderRecord.getUserId());
        }

        int orderDays = Integer.parseInt(orderRecord.getOrderDays());

        orderRecord.setOrderStatus(StatusEnum.ACTIVATED.code);
        TOrderRecord updateOrderRecord = new TOrderRecord();
        updateOrderRecord.setId(orderRecord.getId());
        updateOrderRecord.setOrderStatus(orderRecord.getOrderStatus());

        if (accountInfo == null) {
            accountInfo = new TXrayAccountInfo();
            accountInfo.setUuid(UUID.randomUUID().toString());
            accountInfo.setUserId(orderRecord.getUserId());
            accountInfo.setEffectiveTime(TimeUtil.getCurrentTime());
            Date expireTime = TimeUtil.getTimeAfter(accountInfo.getEffectiveTime(), orderDays);
            accountInfo.setExpireTime(expireTime);
            accountInfo.setAccountStatus(StatusEnum.VALID.code);
            accountBiz.doActiveSave(accountInfo, updateOrderRecord);
        } else {

            TXrayAccountInfo updateXrayAccountInfo = new TXrayAccountInfo();

            if (StatusEnum.VALID.code.equals(accountInfo.getAccountStatus())) {
                Date expireTime = TimeUtil.getTimeAfter(accountInfo.getExpireTime(), orderDays);
                updateXrayAccountInfo.setId(accountInfo.getId());
                updateXrayAccountInfo.setExpireTime(expireTime);
            } else {
                accountInfo.setEffectiveTime(TimeUtil.getCurrentTime());
                Date expireTime = TimeUtil.getTimeAfter(accountInfo.getEffectiveTime(), orderDays);
                updateXrayAccountInfo.setId(accountInfo.getId());
                updateXrayAccountInfo.setExpireTime(expireTime);
            }
            accountBiz.doActiveUpdate(updateXrayAccountInfo, updateOrderRecord);
        }

        xrayService.syncClient();

        log.info("activeOrder service END orderId: [{}]", orderId);

    }

}
