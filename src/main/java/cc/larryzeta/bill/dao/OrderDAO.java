package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entities.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;


import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
public interface OrderDAO {

    @Select(value = "SELECT `order`.oid, `order`.uid, `order`.isActivated, `order`.days, `user`.username FROM `order`  INNER JOIN `user` WHERE `order`.uid = `user`.uid")
    List<Order> getAllOrders();

    @Select(value = "SELECT * FROM `order` WHERE isActivated = 0")
    List<Order> getNotActiveOrders();

    @Select(value = "SELECT * FROM `order` WHERE oid = #{oid}")
    Order getOrderByOid(@Param("oid") String oid);

    @Select(value = "SELECT `order`.oid, `order`.uid, `order`.isActivated, `order`.days, `user`.username FROM `order`  INNER JOIN `user` WHERE `order`.uid = `user`.uid AND `order`.uid = #{uid}")
    List<Order> getOrdersByUid(@Param("uid") Integer uid);

    @Insert(value = "INSERT INTO `order` (oid, uid, days) VALUE (#{oid}, #{uid}, #{days})")
    Integer addOrder(@PathParam("oid") String oid, @PathParam("uid") Integer uid,@PathParam("days") Integer days);

    @Update(value = "UPDATE `order` SET isActivated = 1 WHERE oid = #{oid}")
    Integer setActiveated(@PathParam("oid") String oid);

    @Delete(value = "DELETE FROM `order` WHERE oid= #{oid}")
    Boolean deleteOrderByOid(@Param("oid") String oid);

}
