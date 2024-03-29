package cc.larryzeta.manager.service;

import cc.larryzeta.manager.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {

    void login(String email, String password, HttpSession session);

    void logout(HttpSession session);

    void register(String username, String email, String password, String retype);

    List<User> getAllUsers();

    Integer deleteUser(Integer uid);

    void sentMail(Integer uid,String subject, String content);

}
