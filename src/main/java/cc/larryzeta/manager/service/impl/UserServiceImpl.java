package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.dao.UserDAO;
import cc.larryzeta.manager.entity.User;
import cc.larryzeta.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Autowired
    private JavaMailSender mailSender;


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
            return userDAO.registerUser(user) == 1;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public Integer deleteUser(Integer uid) {
        return userDAO.deleteUser(uid);
    }

    @Override
    public void sentMail(Integer uid,String subject, String content) {
        User user = userDAO.getUserByUid(uid);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("v@larryzeta.cc");
        message.setTo(user.getEmail());
        message.setSubject(subject);
        message.setText("尊敬的用户 " + user.getUsername() + "：\n\n" + content);
        try {
            mailSender.send(message);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
