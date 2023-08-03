package cc.larryzeta.manager.compoment;

import cc.larryzeta.manager.service.AccountService;
import cc.larryzeta.manager.service.NoticeService;
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

    @Scheduled(cron = "0 0 14 * * ?")
    public void refreshAccounts() {
        accountService.refreshAccounts();
    }

}
