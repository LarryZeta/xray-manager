package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entities.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
public interface OrderDAO {

    @Select(value = "SELECT * FROM `order`")
    List<Order> getAllOrders();

    @Select(value = "SELECT * FROM `order` WHERE isActivated = 0")
    List<Order> getNotActiveOrders();

    @Select(value = "SELECT * FROM `order` WHERE uid = #{uid}")
    Order findOrderByUid(@Param("uid") Integer uid);

    @Insert(value = "INSERT INTO `order` (oid, uid, days) VALUE (#{oid}, #{uid}, #{days})")
    Integer addOrder(@PathParam("oid") String oid, @PathParam("uid") Integer uid,@PathParam("days") Integer days);

}
