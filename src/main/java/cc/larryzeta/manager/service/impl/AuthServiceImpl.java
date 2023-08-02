package cc.larryzeta.manager.service.impl;


import cc.larryzeta.manager.api.auth.model.AuthRequest;
import cc.larryzeta.manager.api.auth.model.AuthResponse;
import cc.larryzeta.manager.biz.UserBiz;
import cc.larryzeta.manager.config.JwtConfig;
import cc.larryzeta.manager.dao.UserBaseInfoDAO;
import cc.larryzeta.manager.dao.UserRoleInfoDAO;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.entity.TUserRoleInfo;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.service.AuthService;
import cc.larryzeta.manager.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserBaseInfoDAO userBaseInfoDAO;

    @Autowired
    private UserRoleInfoDAO userRoleInfoDAO;

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public void login(String email, String password, Map<String, Object> map, HttpSession session) {

        log.info("[UserServiceImpl-login] 用户登录 email=[{}]", email);

        if (!StringUtils.hasText(email)) {
            log.info("[UserServiceImpl-login] 用户登录 email=[{}] 失败", email);
            map.put("msg", "Please input email");
            throw new BizException(ReturnCodeEnum.PARAM_EXCEPTION);
        }

        TUserBaseInfo query = new TUserBaseInfo();
        query.setEmail(email);
        TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfo(query).get(0);

        if (userBaseInfo == null) {
            throw new BizException(ReturnCodeEnum.EXCEPTION);
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
            throw new BizException(ReturnCodeEnum.EXCEPTION);
        }
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {

        log.info("login service START authRequest: [{}]", authRequest);

        AuthResponse authResponse = new AuthResponse();

        TUserBaseInfo userBaseInfo = userBiz.getUserByEmail(authRequest.getEmail());

        if (userBaseInfo == null) {
            log.error("login service user not fund email: [{}]", authRequest.getEmail());
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "user not fund");
        }

        if (!userBaseInfo.getPasswd().equals(authRequest.getPassword())) {
            log.error("login service incorrect password");
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "incorrect password");
        }

        String token = JwtUtil.sign(userBaseInfo.getUserName(), userBaseInfo.getEmail(), jwtConfig);
        authResponse.setToken(token);

        log.info("login service END authResponse: [{}]", authResponse);

        return authResponse;
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("loginUser");
        session.removeAttribute("uid");
        session.removeAttribute("isAdmin");
    }

}
