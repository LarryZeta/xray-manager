package cc.larryzeta.manager.biz;

import cc.larryzeta.manager.dao.UserBaseInfoDAO;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.enumeration.StatusEnum;
import cc.larryzeta.manager.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserBiz {

    @Autowired
    private UserBaseInfoDAO userBaseInfoDAO;


    public void deleteUser(int userId) {

        TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfoById(userId);

        if (userBaseInfo == null || StatusEnum.DELETED.code.equals(userBaseInfo.getUserStatus())) {
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "user has deleted");
        }

        userBaseInfo = new TUserBaseInfo();
        userBaseInfo.setId(userId);
        userBaseInfo.setUserStatus(StatusEnum.DELETED.code);
        userBaseInfoDAO.updateTUserBaseInfo(userBaseInfo);

    }

    public TUserBaseInfo getUserByEmail(String email) {

        TUserBaseInfo query = new TUserBaseInfo();
        query.setEmail(email);

        List<TUserBaseInfo> userBaseInfoList = userBaseInfoDAO.getTUserBaseInfo(query);

        if (userBaseInfoList == null || userBaseInfoList.isEmpty()) {
            log.warn("user not found email: [{}]", email);
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "user not found");
        }

        if (userBaseInfoList.size() != 1) {
            log.error("found too many user info email: [{}]", email);
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "too many user info");
        }

        return userBaseInfoList.get(0);

    }

    public void jwtPermission(String email) {

        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        String[] strings = principal.split("-");
        String jwtUserName = strings[0];
        String jwtEmail = strings[1];

        log.info("jwtUserName: [{}]", jwtUserName);
        log.info("jwtEmail: [{}]", jwtEmail);

        if (!jwtEmail.equals(email)) {
            log.warn("permission denied");
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "permission denied");
        }

    }

    public void jwtPermission(Integer userId) {

        TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfoById(userId);

        if (userBaseInfo == null || StatusEnum.INVALID.code.equals(userBaseInfo.getUserStatus())) {
            log.warn("invalid user");
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "invalid user");
        }

        this.jwtPermission(userBaseInfo.getEmail());

    }

}
