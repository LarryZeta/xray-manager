package cc.larryzeta.bill.service;

import cc.larryzeta.bill.entities.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Boolean addOrder(Integer uid, Integer dayes);

}
