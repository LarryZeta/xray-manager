package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entities.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDAO {

    @Select(value = "SELECT * FROM `user`")
    List<User> getAllUsers();

    @Select(value = "SELECT * FROM `user` WHERE user.email = #{email}")
    User getUserByEmail(@Param("email") String email);

    @Insert(value = "INSERT INTO `user` (username, email, password) VALUE (#{username}, #{email}, #{password})")
    Integer registerUser(User user);

}
