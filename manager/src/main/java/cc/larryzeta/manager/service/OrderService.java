package cc.larryzeta.manager.service;

import cc.larryzeta.manager.api.order.model.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getOrders(OrderDTO orderDTO);

    List<OrderDTO> getOrdersByUserId(Integer userId);

    OrderDTO getOrderByOrderId(String orderId);

    void addOrder(OrderDTO orderDTO);

    void deleteOrder(String orderId);

    void activeOrder(String orderId);

}
