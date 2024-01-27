package cc.larryzeta.manager.biz;

import cc.larryzeta.manager.dao.OrderRecordDAO;
import cc.larryzeta.manager.dao.XrayAccountInfoDAO;
import cc.larryzeta.manager.entity.TOrderRecord;
import cc.larryzeta.manager.entity.TXrayAccountInfo;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.enumeration.StatusEnum;
import cc.larryzeta.manager.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AccountBiz {

    @Autowired
    private XrayAccountInfoDAO xrayAccountInfoDAO;

    @Autowired
    private OrderRecordDAO orderRecordDAO;

    public void deleteAccountByUserId(Integer userId) {

        TXrayAccountInfo xrayAccountInfo = getAccount(userId);

        TXrayAccountInfo update = new TXrayAccountInfo();
        update.setId(xrayAccountInfo.getId());
        update.setAccountStatus(StatusEnum.INVALID.code);

        xrayAccountInfoDAO.updateTXrayAccountInfo(update);

    }

    public TXrayAccountInfo getAccount(Integer userId) {

        TXrayAccountInfo query = new TXrayAccountInfo();
        query.setUserId(userId);

        List<TXrayAccountInfo> xrayAccountInfoList = xrayAccountInfoDAO.getTXrayAccountInfo(query);

        if (xrayAccountInfoList == null || xrayAccountInfoList.isEmpty()) {
            log.error("AccountBiz deleteAccountByUserId userId: [{}] no account", userId);
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "account not found");
        }

        if (xrayAccountInfoList.size() != 1) {
            log.error("too many account info userId: [{}]", userId);
            throw new BizException(ReturnCodeEnum.EXCEPTION.code, "too many account info");
        }

        return xrayAccountInfoList.get(0);

    }

    @Transactional
    public void doActiveSave(TXrayAccountInfo accountInfo, TOrderRecord updateOrderRecord) {
        xrayAccountInfoDAO.saveXrayAccountInfo(accountInfo);
        orderRecordDAO.updateTOrderRecord(updateOrderRecord);
    }

    @Transactional
    public void doActiveUpdate(TXrayAccountInfo updateXrayAccountInfo, TOrderRecord updateOrderRecord) {
        xrayAccountInfoDAO.updateTXrayAccountInfo(updateXrayAccountInfo);
        orderRecordDAO.updateTOrderRecord(updateOrderRecord);
    }

}
