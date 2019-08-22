package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entities.Account;
import org.apache.ibatis.annotations.*;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.List;

@Mapper
public interface AccountDAO {

    @Select(value = "SELECT * FROM `account` WHERE uid = #{uid}")
    Account getAccountByUid(@PathParam("uid") Integer uid);

    @Select(value = "SELECT uid FROM `account` WHERE expireDate < #{currentDate}")
    List<Integer> getExpiredAccounts(@PathParam("currentDate") Date currentDate);

    @Insert(value = "INSERT INTO `account` (aid, uid, activationDate, expireDate) VALUE (#{aid}, #{uid}, #{activationDate}, #{expireDate})")
    Integer addAccount(Account account);

    @Update(value = "UPDATE `account` SET expireDate = #{expireDate} WHERE uid = #{uid}")
    Integer updateAccount(Account account);

    @Delete(value = "DELETE FROM `account` WHERE expireDate < #{currentDate}")
    Integer deleteExpiredAccounts(@PathParam("currentDate") Date currentDate);
}
