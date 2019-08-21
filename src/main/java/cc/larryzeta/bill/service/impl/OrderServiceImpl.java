package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.dao.OrderDAO;
import cc.larryzeta.bill.entities.Order;
import cc.larryzeta.bill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;


    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public List<Order> getNotActiveOrders() {
        return orderDAO.getNotActiveOrders();
    }

    @Override
    public Order getOrderByOid(String oid) {
        return orderDAO.getOrderByOid(oid);
    }

    @Override
    public Integer addOrder(Integer uid, Integer days) {
        return orderDAO.addOrder(UUID.randomUUID().toString(), uid, days);
    }



}
