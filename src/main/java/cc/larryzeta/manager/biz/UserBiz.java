package cc.larryzeta.manager.biz;

import cc.larryzeta.manager.dao.UserBaseInfoDAO;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.enumeration.StatusEnum;
import cc.larryzeta.manager.exception.ReturnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBiz {

    @Autowired
    private UserBaseInfoDAO userBaseInfoDAO;

    public void deleteUser(int userId) {

        TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfoById(userId);

        if (userBaseInfo == null || StatusEnum.DELETED.code.equals(userBaseInfo.getUserStatus())) {
            throw new ReturnException("用户无效");
        }

        userBaseInfo = new TUserBaseInfo();
        userBaseInfo.setId(userId);
        userBaseInfo.setUserStatus(StatusEnum.DELETED.code);
        userBaseInfoDAO.updateTUserBaseInfo(userBaseInfo);

    }

}
