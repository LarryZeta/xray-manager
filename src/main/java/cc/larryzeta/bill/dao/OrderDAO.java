package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDAO {

    @Select(value = "SELECT * FROM order")
    List<Order> getAllOrders();

}
