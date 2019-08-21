package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entities.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.websocket.server.PathParam;

@Mapper
public interface AccountDAO {

    @Select(value = "SELECT * FROM `account` WHERE uid = #{uid}")
    Account getAccountByUid(@PathParam("uid") Integer uid);

    @Insert(value = "INSERT INTO `account` (aid, uid, activationDate, expireDate) VALUE (#{aid}, #{uid}, #{activationDate}, #{expireDate})")
    Integer addAccount(Account account);

    @Update(value = "UPDATE `account` SET expireDate = #{expireDate} WHERE uid = #{uid}")
    Integer updateAccount(Account account);

}
