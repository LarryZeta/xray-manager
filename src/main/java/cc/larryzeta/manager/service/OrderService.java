package cc.larryzeta.manager.service;

import cc.larryzeta.manager.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    List<Order> getNotActiveOrders();

    List<Order> getOrdersByUid(Integer uid);

    Order getOrderByOid(String oid);

    Integer addOrder(Integer uid, Integer dayes);

    Boolean deleteOrder(String oid);

}
