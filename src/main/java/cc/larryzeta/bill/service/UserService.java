package cc.larryzeta.bill.service;

import cc.larryzeta.bill.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {

    Boolean login(String email, String password, Map<String, Object> map, HttpSession session);

    void logout(HttpSession session);

    Boolean register(String username, String email, String password, String retype, Map<String, Object> map);

    List<User> getAllUsers();

    Integer deleteUser(Integer uid);

    void sentMail(Integer uid,String subject, String content);

}
