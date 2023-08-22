package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.api.user.model.RegisterRequest;
import cc.larryzeta.manager.api.user.model.UpdatePassWordRequest;
import cc.larryzeta.manager.biz.AccountBiz;
import cc.larryzeta.manager.biz.UserBiz;
import cc.larryzeta.manager.dao.UserBaseInfoDAO;
import cc.larryzeta.manager.dao.UserRoleInfoDAO;
import cc.larryzeta.manager.entity.TUserRoleInfo;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.service.UserService;
import cc.larryzeta.manager.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBaseInfoDAO userBaseInfoDAO;

    @Autowired
    private UserRoleInfoDAO userRoleInfoDAO;

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private AccountBiz accountBiz;


    @Override
    public void register(RegisterRequest registerRequest) {

        log.info("register service START registerRequest: [{}]", JsonUtils.toJSONString(registerRequest));

        TUserBaseInfo userBaseInfo = null;
        try {
            userBaseInfo = userBiz.getUserByEmail(registerRequest.getEmail());
        } catch (BizException bizException) {
            log.info("getUserByEmail BizException", bizException);
        }

        if (userBaseInfo != null) {
            log.warn("The email has been registered email: [{}]", registerRequest.getEmail());
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "The email has been registered email");
        } else if ("admin".equals(registerRequest.getUserName())) {
            log.warn("Username cannot be admin.");
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "Username cannot be admin.");
        } else {
            TUserBaseInfo tUserBaseInfo = new TUserBaseInfo();
            tUserBaseInfo.setUserName(registerRequest.getUserName());
            tUserBaseInfo.setEmail(registerRequest.getEmail());
            tUserBaseInfo.setPasswd(registerRequest.getPassword());
            userBaseInfoDAO.saveTUserBaseInfo(tUserBaseInfo);
        }

        log.info("register service END");

    }

    @Override
    public void deleteUser(String email) {

        log.info("deleteUser service START email: [{}]", email);

        if (!StringUtils.hasText(email)) {
            log.error("deleteUser service email blank");
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "deleteUser email blank");
        }

        TUserBaseInfo userBaseInfo = userBiz.getUserByEmail(email);

        try {
            accountBiz.deleteAccountByUserId(userBaseInfo.getId());
        } catch (BizException bizException) {
            log.info("deleteAccount bizException", bizException);
        }

        userBiz.deleteUser(userBaseInfo.getId());

        log.info("deleteUser service END email: [{}]", email);

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

    @Override
    public List<TUserBaseInfo> getUsers(TUserBaseInfo userBaseInfo) {

        log.info("getUsers service START condition userBaseInfo: [{}]", JsonUtils.toJSONString(userBaseInfo));

        List<TUserBaseInfo> userBaseInfoList = userBaseInfoDAO.getTUserBaseInfo(userBaseInfo);

        log.info("getUsers service END userBaseInfoList: [{}]", JsonUtils.toJSONString(userBaseInfoList));

        return userBaseInfoList;
    }

    @Override
    public TUserBaseInfo getUser(String email) {

        log.info("getUser service START email: {}", email);

        userBiz.jwtPermission(email);

        TUserBaseInfo userBaseInfo = userBiz.getUserByEmail(email);

        log.info("getUser service END userBaseInfo: {}", JsonUtils.toJSONString(userBaseInfo));

        return userBaseInfo;
    }

    @Override
    public TUserBaseInfo updatePassWord(String email, UpdatePassWordRequest updatePassWordRequest) {

        log.info("updatePassWord service START email: [{}] updatePassWordRequest: [{}]", email, JsonUtils.toJSONString(updatePassWordRequest));

        if (!StringUtils.hasText(email)) {
            log.error("updatePassWord service email blank");
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "email blank");
        }

        userBiz.jwtPermission(email);

        if (!email.equals(updatePassWordRequest.getEmail())) {
            log.error("updatePassWord service not same email");
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "not same email");
        }

        TUserBaseInfo userBaseInfo = userBiz.getUserByEmail(email);

        if (!userBaseInfo.getPasswd().equals(updatePassWordRequest.getPassword())) {
            log.error("updatePassWord service incorrect password");
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "incorrect password");
        }

        TUserBaseInfo update = new TUserBaseInfo();
        update.setId(userBaseInfo.getId());
        update.setPasswd(updatePassWordRequest.getNewPassWord());

        userBaseInfoDAO.updateTUserBaseInfo(update);

        userBaseInfo = userBaseInfoDAO.getTUserBaseInfoById(update.getId());

        log.info("updatePassWord service END userBaseInfo: [{}]", JsonUtils.toJSONString(userBaseInfo));

        return userBaseInfo;
    }

}
