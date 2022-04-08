package cc.larryzeta.manager.biz;

import cc.larryzeta.manager.dao.TUserBaseInfoDAO;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.enumeration.StatusEnum;
import cc.larryzeta.manager.exception.ReturnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBiz {

    @Autowired
    private TUserBaseInfoDAO userBaseInfoDAO;

    public void deleteUser(String userId) {

        TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfoById(userId);

        if (userBaseInfo == null || StatusEnum.DELETED.code.equals(userBaseInfo.getStatus())) {
            throw new ReturnException("用户无效");
        }

        userBaseInfo = new TUserBaseInfo();
        userBaseInfo.setId(userId);
        userBaseInfo.setStatus(StatusEnum.DELETED.code);
        userBaseInfoDAO.updateTUserBaseInfo(userBaseInfo);

    }

}
