package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@Mapper
public interface AccountDAO {

    @Select(value = "SELECT * FROM `account` WHERE uid = #{uid}")
    Account getAccountByUid(@PathParam("uid") Integer uid);

    @Select(value = "SELECT `account`.aid, `account`.uid, `account`.activationDate, `account`.expireDate, `user`.username, `user`.email FROM `account` INNER JOIN `user` WHERE `account`.uid = `user`.uid")
    List<Account> getAllAccount();

    @Select(value = "SELECT uid FROM `account` WHERE expireDate < #{date}")
    List<Integer> getExpiredAccounts(@PathParam("date") Date date);

    @Insert(value = "INSERT INTO `account` (aid, uid, activationDate, expireDate) VALUE (#{aid}, #{uid}, #{activationDate}, #{expireDate})")
    Integer addAccount(Account account);

    @Update(value = "UPDATE `account` SET expireDate = #{expireDate} WHERE uid = #{uid}")
    Integer updateAccount(Account account);

    @Delete(value = "DELETE FROM `account` WHERE expireDate < #{currentDate}")
    Integer deleteExpiredAccounts(@PathParam("currentDate") Date currentDate);

    @Delete(value = "DELETE FROM `account` WHERE aid = #{aid}")
    Integer deleteAccount(@PathParam("aid") String aid);

    @Delete(value = "DELETE FROM `account` WHERE uid = #{uid}")
    Integer deleteAccountByUid(@PathParam("uid") Integer uid);

}
