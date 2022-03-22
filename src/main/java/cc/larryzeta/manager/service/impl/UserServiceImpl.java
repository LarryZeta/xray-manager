package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.dao.TUserBaseInfoDAO;
import cc.larryzeta.manager.mapper.UserDAO;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.entity.User;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.exception.ReturnException;
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
    private TUserBaseInfoDAO tUserBaseInfoDAO;



    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void login(String email, String password, Map<String, Object> map, HttpSession session) {

        log.info("[UserServiceImpl-login] 用户登录 email=[{}]", email);

        if (StringUtils.isEmpty(email)) {
            log.info("[UserServiceImpl-login] 用户登录 email=[{}] 失败", email);
            map.put("msg", "Please input email");
            throw new ReturnException(ReturnCodeEnum.PARAM_EXCEPTION);
        }

        TUserBaseInfo userBaseInfo = tUserBaseInfoDAO.getTUserBaseInfoByEmail(email);

//        TUserRoleRelation userRoleRelation =

        if (userBaseInfo == null) {
            throw new ReturnException(ReturnCodeEnum.EXCEPTION);
        }

        if (userBaseInfo.getPasswd().equals(password)) {
            log.info("[UserServiceImpl-login] 用户登录成功 email=[{}]", email);
            session.setAttribute("loginUser", userBaseInfo.getUserName());
            session.setAttribute("uid", userBaseInfo.getId());
//            session.setAttribute("isAdmin", userBaseInfo.getIsAdmin());
        } else {
            log.info("[UserServiceImpl-login] 用户登录 email=[{}] 失败", email);
            map.put("msg", "Invalid email or password.");
            throw new ReturnException(ReturnCodeEnum.EXCEPTION);
        }
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("loginUser");
        session.removeAttribute("uid");
        session.removeAttribute("isAdmin");
    }

    @Override
    public void register(String username, String email, String password, String retype, Map<String, Object> map) {

        log.info("UserServiceImpl-register username=[{}], email=[{}]", username, email);

        User exist = userDAO.getUserByEmail(email);
        if (exist != null) {
            map.put("msg", "The email has been registered.");
            throw new ReturnException(ReturnCodeEnum.EXCEPTION);
        } else if (!password.equals(retype)) {
            map.put("msg", "Inconsistent password.");
            throw new ReturnException(ReturnCodeEnum.EXCEPTION);
        } else if (username.equals("admin")) {
            map.put("msg", "Username cannot be admin.");
            throw new ReturnException(ReturnCodeEnum.EXCEPTION);
        } else {
            TUserBaseInfo tUserBaseInfo = new TUserBaseInfo();
            tUserBaseInfo.setUserName(username);
            tUserBaseInfo.setEmail(email);
            tUserBaseInfo.setPasswd(password);
            tUserBaseInfoDAO.saveTUserBaseInfo(tUserBaseInfo);

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            userDAO.registerUser(user);
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
