package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.biz.AccountBiz;
import cc.larryzeta.manager.biz.UserBiz;
import cc.larryzeta.manager.config.AccountConfig;
import cc.larryzeta.manager.dao.XrayAccountInfoDAO;
import cc.larryzeta.manager.entity.TXrayAccountInfo;
import cc.larryzeta.manager.service.AccountService;
import cc.larryzeta.manager.service.NoticeService;
import cc.larryzeta.manager.service.XrayService;
import cc.larryzeta.manager.util.JsonUtils;
import cc.larryzeta.manager.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountConfig accountConfig;

    @Autowired
    private AccountBiz accountBiz;

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private XrayService xrayService;

    @Autowired
    private XrayAccountInfoDAO xrayAccountInfoDAO;

    @Override
    public TXrayAccountInfo getAccount(Integer userId) {

        log.info("getAccount Service START userId: [{}]", userId);

        userBiz.jwtPermission(userId);

        TXrayAccountInfo xrayAccountInfo = accountBiz.getAccount(userId);

        log.info("getAccount Service END TXrayAccountInfo: [{}]", JsonUtils.toJSONString(xrayAccountInfo));

        return xrayAccountInfo;
    }

    @Override
    public List<TXrayAccountInfo> getAccounts(TXrayAccountInfo xrayAccountInfo) {

        log.info("getAllAccount service START condition xrayAccountInfo: [{}]", JsonUtils.toJSONString(xrayAccountInfo));

        List<TXrayAccountInfo> xrayAccountInfoList = xrayAccountInfoDAO.getTXrayAccountInfo(xrayAccountInfo);

        log.info("getAllAccount service END userBaseInfoList: [{}]", JsonUtils.toJSONString(xrayAccountInfoList));

        return xrayAccountInfoList;

    }

    @Override
    public void deleteAccount(Integer userId) {

        log.info("deleteAccount Service START userId: [{}]", userId);

        accountBiz.deleteAccountByUserId(userId);

        noticeService.sentNotice(userId, "账号删除提醒", "您的账号已被删除。");

        noticeService.sentNotice(userId, "账号到期提醒", "您的账号已到期\n，因系统故障未提醒\n 所以保存账号及功能到10月3日 未续期将删除，请及时续费\n" );

        xrayService.syncClient();

        log.info("deleteAccount Service END");

    }

    @Override
    public void refreshAccounts() {

        log.info("refreshAccounts START");

        Date now = TimeUtil.getCurrentTime();

        List<TXrayAccountInfo> xrayAccountInfoList = xrayAccountInfoDAO.getExpiredAccounts(now);

        log.info("refreshAccounts expired xrayAccountInfoList: [{}]", JsonUtils.toJSONString(xrayAccountInfoList));

        for (TXrayAccountInfo xrayAccountInfo : xrayAccountInfoList) {
            try {
                this.deleteAccount(xrayAccountInfo.getUserId());
            } catch (Exception e) {
                log.error("deleteAccount userId: [{}] error", xrayAccountInfo.getUserId(), e);
            }
        }

        Date warnedDate = TimeUtil.getTimeAfter(accountConfig.getNoticeBeforeDays());

        xrayAccountInfoList = xrayAccountInfoDAO.getExpiredAccounts(warnedDate);

        log.info("refreshAccounts send warn notice xrayAccountInfoList: [{}]", JsonUtils.toJSONString(xrayAccountInfoList));

        for (TXrayAccountInfo xrayAccountInfo : xrayAccountInfoList) {
            try {
                noticeService.sentNotice(xrayAccountInfo.getUserId(), "账号到期提醒", "您的账号有效期已不足" + accountConfig.getNoticeBeforeDays() + "天, 到期时间： " + xrayAccountInfo.getExpireTime() + "\n 过期将删除（配置文件）。\n\n详情 https://v.larryzeta.cc/account。");
            } catch (Exception e) {
                log.error("noticeAccount userId: [{}] error", xrayAccountInfo.getUserId(), e);
            }
        }

        log.info("refreshAccounts END");

    }

}
