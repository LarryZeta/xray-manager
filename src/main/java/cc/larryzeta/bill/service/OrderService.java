package cc.larryzeta.bill.service;

import cc.larryzeta.bill.entities.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    List<Order> getNotActiveOrders();

    List<Order> getOrdersByUid(Integer uid);

    Order getOrderByOid(String oid);

    Integer addOrder(Integer uid, Integer dayes);

}
