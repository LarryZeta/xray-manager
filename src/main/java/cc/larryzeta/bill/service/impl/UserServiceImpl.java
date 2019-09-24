package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.dao.UserDAO;
import cc.larryzeta.bill.entities.User;
import cc.larryzeta.bill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public Boolean login(String email, String password, Map<String, Object> map, HttpSession session) {
        User user = userDAO.getUserByEmail(email);
        if (!StringUtils.isEmpty(email) && user != null && user.getPassword().equals(password)) {
            session.setAttribute("loginUser", user.getUsername());
            session.setAttribute("uid", user.getUid());
            session.setAttribute("isAdmin", user.getIsAdmin());
            return true;
        } else {
            map.put("msg", "Invalid email or password.");
            return false;
        }
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("loginUser");
        session.removeAttribute("uid");
        session.removeAttribute("isAdmin");
    }

    @Override
    public Boolean register(String username, String email, String password, String retype, Map<String, Object> map) {
        User exist = userDAO.getUserByEmail(email);
        if (exist != null) {
            map.put("msg", "The email has been registered.");
            return false;
        } else if (!password.equals(retype)) {
            map.put("msg", "Inconsistent password.");
            return false;
        } else if (username.equals("admin")) {
            map.put("msg", "Username cannot be admin.");
            return false;
        } else {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            userDAO.registerUser(user);
            return true;
        }
    }

    @Override
    public Boolean isAdmin(Integer uid) {
        return userDAO.getUserByUid(uid).getIsAdmin();
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

}
