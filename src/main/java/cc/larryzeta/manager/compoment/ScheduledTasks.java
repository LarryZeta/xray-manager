package cc.larryzeta.manager.compoment;

import cc.larryzeta.manager.service.AccountService;
import cc.larryzeta.manager.service.UserService;
import cc.larryzeta.manager.service.XrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private AccountService accountService;
    @Autowired
    private XrayService xrayService;
    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0 14 * * ?")
    public void sendWarnEmail() {
        Integer days = 7;
        List<Integer> uids = accountService.getWarnedAccounts(days);
        if (!uids.isEmpty()) {
            for (Integer uid : uids) {
                userService.sentMail(uid, "账号到期提醒", "您的账号有效期已不足" + days + "天, 过期将删除（配置文件）。\n\n详情 https://v.larryzeta.cc/account。");
            }
        }
    }

    @Scheduled(cron = "0 0 15 * * ?")
    public void deleteAccount() {
        List<Integer> uids = accountService.deleteExpiredAccounts();
        if (!uids.isEmpty()) {
            System.out.println("ExpiredAccounts:" + uids);
        }
        for (Integer uid : uids) {
            userService.sentMail(uid, "账号删除提醒", "您的账号已被删除。");
            xrayService.deleteClient(uid);
            xrayService.syncConfig();
        }
    }

}
