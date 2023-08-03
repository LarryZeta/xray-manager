package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.api.order.model.OrderDTO;
import cc.larryzeta.manager.biz.UserBiz;
import cc.larryzeta.manager.dao.OrderRecordDAO;
import cc.larryzeta.manager.dao.UserRoleInfoDAO;
import cc.larryzeta.manager.entity.Account;
import cc.larryzeta.manager.entity.TOrderRecord;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.enumeration.StatusEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.mapper.AccountDAO;
import cc.larryzeta.manager.mapper.OrderDAO;
import cc.larryzeta.manager.entity.Order;
import cc.larryzeta.manager.service.OrderService;
import cc.larryzeta.manager.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.*;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDAO orderDAO;

    @Resource
    private AccountDAO accountDAO;

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private OrderRecordDAO orderRecordDAO;

    @Override
    public List<OrderDTO> getOrders(OrderDTO orderDTO) {

        TOrderRecord query = new TOrderRecord();
        BeanUtils.copyProperties(orderDTO, query);

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

        return orderDTOList;
    }

    @Override
    public List<Order> getNotActiveOrders() {
        return orderDAO.getNotActiveOrders();
    }

    @Override
    public List<Order> getOrdersByUid(Integer uid) {
        return orderDAO.getOrdersByUid(uid);
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

        Integer orderDays = Integer.getInteger(orderDTO.getOrderDays());
        Integer orderPrice = Integer.getInteger(orderDTO.getOrderPrice());

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

        log.info("deleteOrder END");

    }

    @Override
    public Integer activeOrder(Order order) {

        Account account = accountDAO.getAccountByUid(order.getUid());

        if (account == null) {
            account = new Account();
            account.setAid(UUID.randomUUID().toString());
            account.setUid(order.getUid());
            Date currentDate = new Date(System.currentTimeMillis());
            account.setActivationDate(currentDate);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, order.getDays());
            account.setExpireDate(new Date(calendar.getTimeInMillis()));
            orderDAO.setActiveated(order.getOid());
            return accountDAO.addAccount(account);
        } else {
            Date expireDate = account.getExpireDate();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(expireDate);
            calendar.add(Calendar.DATE, order.getDays());
            account.setExpireDate(new Date(calendar.getTimeInMillis()));
            orderDAO.setActiveated(order.getOid());
            return accountDAO.updateAccount(account);
        }

    }

}
