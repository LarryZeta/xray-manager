package cc.larryzeta.bill.compoment;

import cc.larryzeta.bill.service.AccountService;
import cc.larryzeta.bill.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private AccountService accountService;
    @Autowired
    private V2rayService v2rayService;

    @Scheduled(fixedRate = 5000)
    public void checkClient() {
        List<Integer> uids = accountService.deleteExpiredAccounts();
        System.out.println(uids);
        for (Integer uid : uids) {
            v2rayService.deleteClient(uid);
        }
    }

}
