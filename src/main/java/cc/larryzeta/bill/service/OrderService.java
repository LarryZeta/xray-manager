package cc.larryzeta.bill.service;

import cc.larryzeta.bill.entities.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    List<Order> getNotActiveOrders();

    Integer addOrder(Integer uid, Integer dayes);

}
