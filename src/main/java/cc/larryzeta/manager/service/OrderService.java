package cc.larryzeta.manager.service;

import cc.larryzeta.manager.api.order.model.OrderDTO;
import cc.larryzeta.manager.entity.Order;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getOrders(OrderDTO orderDTO);

    List<Order> getOrdersByUid(Integer uid);

    OrderDTO getOrderByOrderId(String orderId);

    void addOrder(OrderDTO orderDTO);

    void deleteOrder(String orderId);

    Integer activeOrder(Order order);

}
