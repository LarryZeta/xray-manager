package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface UserDAO {

    @Select(value = "SELECT * FROM `user`")
    List<User> getAllUsers();

    @Select(value = "SELECT * FROM `user` WHERE user.uid = #{uid}")
    User getUserByUid(@Param("uid") Integer uid);

    @Select(value = "SELECT * FROM `user` WHERE user.email = #{email}")
    User getUserByEmail(@Param("email") String email);

    @Insert(value = "INSERT INTO `user` (username, email, password) VALUE (#{username}, #{email}, #{password})")
    Integer registerUser(User user);

    @Delete(value = "DELETE FROM `user` WHERE uid= #{uid}")
    Integer deleteUser(@Param("uid") Integer uid);

}
