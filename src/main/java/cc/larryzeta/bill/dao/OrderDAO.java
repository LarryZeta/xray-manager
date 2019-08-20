package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDAO {

    @Select(value = "SELECT * FROM order")
    List<Order> getAllOrders();

    @Select(value = "SELECT * FROM order WHERE uid = #{uid}")
    Order findOrderByUid(@Param("uid") Integer uid);

}
