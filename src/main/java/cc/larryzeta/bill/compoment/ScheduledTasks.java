package cc.larryzeta.bill.compoment;

import cc.larryzeta.bill.service.AccountService;
import cc.larryzeta.bill.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private AccountService accountService;
    @Autowired
    private V2rayService v2rayService;
    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(cron = "0 59 13 * * ?")
    public void checkClient() {
        List<Integer> uids = accountService.deleteExpiredAccounts();
        System.out.println(uids);
        for (Integer uid : uids) {
            v2rayService.deleteClient(uid);
        }
    }

    @Scheduled(cron = "0 0 14 * * ?")
    public Boolean sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("i@larryzeta.cc");
        msg.setBcc();
        msg.setTo("i@larryzeta.cc");
        msg.setSubject("jp.larryzeta.cc");
        msg.setText("测试邮件");
        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }

}
