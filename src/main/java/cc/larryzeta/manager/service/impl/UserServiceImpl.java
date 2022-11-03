package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.dao.UserDAO;
import cc.larryzeta.manager.entity.User;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void login(String email, String password, HttpSession session) {

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            log.warn("email 和 password 不能为空");
            throw new BizException("3000", "邮箱和密码不能为空");
        }

        User user = userDAO.getUserByEmail(email);

        if (user == null) {
            throw new BizException("4000", "用户不存在");
        }

        if (!user.getPassword().equals(password)) {
            throw new BizException("6000", "密码错误");
        }

        session.setAttribute("loginUser", user.getUsername());
        session.setAttribute("uid", user.getUid());
        session.setAttribute("isAdmin", user.getIsAdmin());

    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("loginUser");
        session.removeAttribute("uid");
        session.removeAttribute("isAdmin");
    }

    @Override
    public void register(String username, String email, String password, String retype) {

        User user = userDAO.getUserByEmail(email);

        if (user != null) {
            throw new BizException("4010", "此邮箱用户已经注册");
        }
        if (!password.equals(retype)) {
            throw new BizException("3010", "两次密码输入不一致");
        }
        if (username.equals("admin")) {
            throw new BizException("6000", "用户名不能为admin");
        }

        else {
            user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            userDAO.save(user);
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
            log.error("发送邮件异常", ex);
        }
    }

}
