package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.api.model.ResultEntity;
import cc.larryzeta.manager.api.order.OrderControllerApi;
import cc.larryzeta.manager.api.order.model.OrderDTO;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.service.OrderService;
import cc.larryzeta.manager.util.JsonUtils;
import io.micrometer.core.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
public class OrderController implements OrderControllerApi {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{userId}/orders")
    public ResultEntity<List<OrderDTO>> getUserOrders(@PathVariable Integer userId) {

        log.info("getUserOrders START userId: [{}]", userId);

        ResultEntity<List<OrderDTO>> resultEntity = new ResultEntity<>();

        try {
            List<OrderDTO> orderDTOList = orderService.getOrdersByUserId(userId);
            resultEntity.setData(orderDTOList);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("getUserOrders bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("getUserOrders unknown Exception e", e);
        }

        log.info("getUserOrders END resultEntity: [{}]", JsonUtils.toJSONString(resultEntity));

        return resultEntity;
    }

    @PostMapping("/order")
    @ResponseBody
    @Override
    public ResultEntity<String> addOrder(@RequestBody @Validated OrderDTO orderDTO) {

        log.info("addOrder START orderDTO: [{}]", JsonUtils.toJSONString(orderDTO));

        ResultEntity<String> resultEntity = new ResultEntity<>();

        try {
            orderService.addOrder(orderDTO);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("addOrder bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("addOrder unknown Exception e", e);
        }

        log.info("addOrder END resultEntity: [{}]", JsonUtils.toJSONString(resultEntity));

        return resultEntity;
    }

    @RequiresRoles("ADMIN")
    @DeleteMapping("/order/{orderId}")
    @ResponseBody
    @Override
    public ResultEntity<String> deleteOrder(@PathVariable String orderId) {

        log.info("deleteOrder START orderId: [{}]", orderId);

        ResultEntity<String> resultEntity = new ResultEntity<>();

        try {
            orderService.deleteOrder(orderId);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("deleteOrder bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("deleteOrder unknown Exception e", e);
        }


        log.info("deleteOrder END resultEntity: [{}]", JsonUtils.toJSONString(resultEntity));

        return resultEntity;
    }

    @GetMapping("/order/{orderId}")
    @ResponseBody
    @Override
    public ResultEntity<OrderDTO> queryOrder(@PathVariable String orderId) {

        log.info("queryOrder START orderId: [{}]", orderId);

        ResultEntity<OrderDTO> resultEntity = new ResultEntity<>();
        try {
            OrderDTO orderDTO = orderService.getOrderByOrderId(orderId);
            resultEntity.setData(orderDTO);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("queryOrder bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("queryOrder unknown Exception e", e);
        }

        log.info("queryOrder END resultEntity: [{}]", JsonUtils.toJSONString(resultEntity));

        return resultEntity;
    }


    @RequiresRoles("ADMIN")
    @ResponseBody
    @GetMapping("/orders")
    @Override
    public ResultEntity<List<OrderDTO>> queryOrders(@RequestBody @Nullable OrderDTO orderDTO) {

        log.info("queryOrders START orderDTO: [{}]", JsonUtils.toJSONString(orderDTO));

        ResultEntity<List<OrderDTO>> resultEntity = new ResultEntity<>();
        try {
            List<OrderDTO> orderDTOList = orderService.getOrders(orderDTO);
            resultEntity.setData(orderDTOList);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("queryOrder bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("queryOrder unknown Exception e", e);
        }

        log.info("queryOrders END resultEntity: [{}]", JsonUtils.toJSONString(resultEntity));

        return resultEntity;
    }

    @RequiresRoles("ADMIN")
    @PutMapping("/order/{orderId}")
    @ResponseBody
    @Override
    public ResultEntity<String> activeOrder(@PathVariable String orderId) {

        log.info("activeOrder START orderId: [{}]", orderId);

        ResultEntity<String> resultEntity = new ResultEntity<>();

        try {
            orderService.activeOrder(orderId);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("activeOrder bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("activeOrder unknown Exception e", e);
        }

        log.info("activeOrder END resultEntity: [{}]", JsonUtils.toJSONString(resultEntity));

        return resultEntity;
    }

}
