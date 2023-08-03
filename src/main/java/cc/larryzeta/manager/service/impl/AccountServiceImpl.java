package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.biz.AccountBiz;
import cc.larryzeta.manager.biz.UserBiz;
import cc.larryzeta.manager.config.AccountConfig;
import cc.larryzeta.manager.dao.XrayAccountInfoDAO;
import cc.larryzeta.manager.entity.TXrayAccountInfo;
import cc.larryzeta.manager.mapper.AccountDAO;
import cc.larryzeta.manager.service.AccountService;
import cc.larryzeta.manager.service.NoticeService;
import cc.larryzeta.manager.service.XrayService;
import cc.larryzeta.manager.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountConfig accountConfig;

    @Resource
    private AccountDAO accountDAO;

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

        xrayService.deleteClient(userId);

        log.info("deleteAccount Service END");

    }

    @Override
    public void refreshAccounts() {

        log.info("refreshAccounts START");

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        List<TXrayAccountInfo> xrayAccountInfoList = xrayAccountInfoDAO.getExpiredAccounts(today);

        log.info("refreshAccounts expired xrayAccountInfoList: [{}]", JsonUtils.toJSONString(xrayAccountInfoList));

        for (TXrayAccountInfo xrayAccountInfo : xrayAccountInfoList) {
            this.deleteAccount(xrayAccountInfo.getUserId());
        }

        calendar.add(Calendar.DATE, accountConfig.getNoticeBeforeDays());
        Date warnedDate = new Date(calendar.getTimeInMillis());

        xrayAccountInfoList = xrayAccountInfoDAO.getExpiredAccounts(warnedDate);

        log.info("refreshAccounts send warn notice xrayAccountInfoList: [{}]", JsonUtils.toJSONString(xrayAccountInfoList));

        for (TXrayAccountInfo xrayAccountInfo : xrayAccountInfoList) {
            noticeService.sentNotice(xrayAccountInfo.getUserId(), "账号到期提醒", "您的账号有效期已不足" + accountConfig.getNoticeBeforeDays() + "天, 到期时间： " + xrayAccountInfo.getExpireTime() + "\n 过期将删除（配置文件）。\n\n详情 https://v.larryzeta.cc/account。");
        }

        log.info("refreshAccounts END");

    }

}
