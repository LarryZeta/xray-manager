package cc.larryzeta.bill.compoment;

import cc.larryzeta.bill.dao.UserDAO;
import cc.larryzeta.bill.entities.User;
import cc.larryzeta.bill.service.AccountService;
import cc.larryzeta.bill.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private AccountService accountService;
    @Autowired
    private V2rayService v2rayService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserDAO userDAO;
    private String hostname;
    {
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            hostname = "larryzeta.cc";
        }
    }


    @Scheduled(cron = "0 0 15 * * ?")
    public void checkClient() {
        List<Integer> uids = accountService.deleteExpiredAccounts();
        if (!uids.isEmpty()) {
            System.out.println("ExpiredAccounts:" + uids);
        }
        for (Integer uid : uids) {
            User user = userDAO.getUserByUid(uid);
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("v@larryzeta.cc");
            msg.setBcc();
            msg.setTo(user.getEmail());
            msg.setSubject(hostname);
            msg.setText("您的账号已被删除");
            try {
                mailSender.send(msg);
            } catch (MailException ex) {
                System.err.println(ex.getMessage());
            }
            v2rayService.deleteClient(uid);
        }
    }

    @Scheduled(cron = "0 0 14 * * ?")
    public void sendWarnEmail() {
        Integer days = 7;
        List<Integer> uids = accountService.getWarnedAccounts(days);
        if (!uids.isEmpty()) {
            for (Integer uid : uids) {
                User user = userDAO.getUserByUid(uid);
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setFrom("v@larryzeta.cc");
                msg.setBcc();
                msg.setTo(user.getEmail());
                msg.setSubject(hostname);
                msg.setText("您的账号有效期已不足" + days + "天");
                try {
                    mailSender.send(msg);
                } catch (MailException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

}
