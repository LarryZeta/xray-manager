package cc.larryzeta.manager.service.impl;


import cc.larryzeta.manager.api.auth.model.AuthRequest;
import cc.larryzeta.manager.api.auth.model.AuthResponse;
import cc.larryzeta.manager.biz.UserBiz;
import cc.larryzeta.manager.config.JwtConfig;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.enumeration.StatusEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.service.AuthService;
import cc.larryzeta.manager.service.UserService;
import cc.larryzeta.manager.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {


    @Autowired
    private UserBiz userBiz;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public AuthResponse login(AuthRequest authRequest) {

        log.info("login service START authRequest: [{}]", authRequest);

        AuthResponse authResponse = new AuthResponse();

        TUserBaseInfo userBaseInfo = userBiz.getUserByEmail(authRequest.getEmail());

        if (userBaseInfo == null) {
            log.error("login service user not found email: [{}]", authRequest.getEmail());
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "user not found");
        }

        if (!userBaseInfo.getPasswd().equals(authRequest.getPassword())) {
            log.error("login service incorrect password");
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "incorrect password");
        }

        if (!StatusEnum.VALID.code.equals(userBaseInfo.getUserStatus())) {
            log.error("login service invalid user");
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "invalid user");
        }

        List<String> roleList = userService.getRoleList(userBaseInfo.getUserName());

        String token = JwtUtil.sign(userBaseInfo.getUserName(), userBaseInfo.getEmail(), jwtConfig);
        authResponse.setToken(token);
        authResponse.setUserBaseInfo(userBaseInfo);
        authResponse.setRoleList(roleList);

        log.info("login service END authResponse: [{}]", authResponse);

        return authResponse;
    }

}
