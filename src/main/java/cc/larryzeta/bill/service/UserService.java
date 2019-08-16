package cc.larryzeta.bill.service;

import cc.larryzeta.bill.entities.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {

    Boolean login(String email, String password, Map<String, Object> map, HttpSession session);

    void logout(HttpSession session);

    Boolean register(String username, String email, String password, String retype, Map<String, Object> map);

}
