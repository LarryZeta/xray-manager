package cc.larryzeta.manager.biz;

import cc.larryzeta.manager.dao.UserBaseInfoDAO;
import cc.larryzeta.manager.dao.XrayAccountInfoDAO;
import cc.larryzeta.manager.entity.Account;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.enumeration.StatusEnum;
import cc.larryzeta.manager.exception.BizException;
import lombok.extern.slf4j.Slf4j;
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
            log.warn("user not fund email: [{}]", email);
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "user not fund");
        }

        if (userBaseInfoList.size() != 1) {
            log.error("fund too many user info email: [{}]", email);
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "too many user info");
        }

        return userBaseInfoList.get(0);

    }

}
