package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.biz.UserBiz;
import cc.larryzeta.manager.dao.UserBaseInfoDAO;
import cc.larryzeta.manager.dao.UserRoleInfoDAO;
import cc.larryzeta.manager.entity.TUserRoleInfo;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Autowired
    private UserBaseInfoDAO userBaseInfoDAO;

    @Autowired
    private UserRoleInfoDAO userRoleInfoDAO;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserBiz userBiz;

    @Override
    public void login(String email, String password, Map<String, Object> map, HttpSession session) {

        log.info("[UserServiceImpl-login] 用户登录 email=[{}]", email);

        if (!StringUtils.hasText(email)) {
            log.info("[UserServiceImpl-login] 用户登录 email=[{}] 失败", email);
            map.put("msg", "Please input email");
            throw new ReturnException(ReturnCodeEnum.PARAM_EXCEPTION);
        }

        TUserBaseInfo query = new TUserBaseInfo();
        query.setEmail(email);
        TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfo(query).get(0);

        if (userBaseInfo == null) {
            throw new ReturnException(ReturnCodeEnum.EXCEPTION);
        }

        TUserRoleInfo userRoleInfo = new TUserRoleInfo();
        userRoleInfo.setUserId(userBaseInfo.getId());
        List<TUserRoleInfo> userRoleInfoList = userRoleInfoDAO.getUserRole(userRoleInfo);

        for (TUserRoleInfo roleInfo : userRoleInfoList) {
            if ("ADMIN".equals(roleInfo.getRoleCode()) && "VALID".equals(roleInfo.getRoleStatus())) {
                // TODO ADMIN
                break;
            }
        }

        if (userBaseInfo.getPasswd().equals(password)) {
            log.info("[UserServiceImpl-login] 用户登录成功 email=[{}]", email);
            session.setAttribute("loginUser", userBaseInfo.getUserName());
            session.setAttribute("uid", userBaseInfo.getId());
            session.setAttribute("isAdmin", true);
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

//        User exist = userDAO.getUserByEmail(email);
        TUserBaseInfo query = new TUserBaseInfo();
        query.setEmail(email);
        TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfo(query).get(0);

        if (userBaseInfo != null) {
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
            userBaseInfoDAO.saveTUserBaseInfo(tUserBaseInfo);

//            User user = new User();
//            user.setUsername(username);
//            user.setEmail(email);
//            user.setPassword(password);
//            userDAO.registerUser(user);
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<TUserBaseInfo> tUserBaseInfoList = userBaseInfoDAO.getTUserBaseInfo(null);

        // TUserBaseInfo -> user
        List<User> userList = tUserBaseInfoList
                .stream()
                .map(this::userBaseInfo2User)
                .collect(Collectors.toList());

        return null;
//        return userDAO.getAllUsers();
    }

    @Override
    public Integer deleteUser(Integer uid) {
//        return userDAO.deleteUser(uid);

        userBiz.deleteUser(uid);
        return 0;
    }

    @Override
    public void sentMail(Integer uid,String subject, String content) {
//        User user = userDAO.getUserByUid(uid);
        TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfoById(uid);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("v@larryzeta.cc");
//        message.setTo(user.getEmail());
        message.setTo(userBaseInfo.getEmail());
        message.setSubject(subject);
//        message.setText("尊敬的用户 " + user.getUsername() + "：\n\n" + content);
        message.setText("尊敬的用户 " + userBaseInfo.getUserName() + "：\n\n" + content);
        try {
            mailSender.send(message);
        } catch (MailException ex) {
            log.error("[UserServiceImpl-sentMail] error", ex);
//            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getRoleList(String userName) {

        TUserBaseInfo userBaseInfo = new TUserBaseInfo();
        userBaseInfo.setUserName(userName);
        List<TUserBaseInfo> userBaseInfoList = userBaseInfoDAO.getTUserBaseInfo(userBaseInfo);

        TUserRoleInfo userRoleInfo = new TUserRoleInfo();
        userRoleInfo.setUserId(userBaseInfoList.get(0).getId());
        List<TUserRoleInfo> userRoleInfoList = userRoleInfoDAO.getUserRole(userRoleInfo);

        return userRoleInfoList.stream().map(TUserRoleInfo::getRoleCode).collect(Collectors.toList());
    }

    @Override
    public List<String> getPermissions(String username) {
        return Arrays.asList("TEST", "READ");
    }

    private User userBaseInfo2User(TUserBaseInfo userBaseInfo) {
        User user = new User();
        user.setEmail(userBaseInfo.getEmail());
        user.setUsername(userBaseInfo.getUserName());
        user.setUid(userBaseInfo.getId());
        user.setPassword(userBaseInfo.getPasswd());
        return user;
    }

}
