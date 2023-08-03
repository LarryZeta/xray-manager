package cc.larryzeta.manager.service.impl;


import cc.larryzeta.manager.dao.UserBaseInfoDAO;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl implements NoticeService {

    @Autowired
    private UserBaseInfoDAO userBaseInfoDAO;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sentNotice(Integer uid,String subject, String content) {
        TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfoById(uid);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("v@larryzeta.cc");
        message.setTo(userBaseInfo.getEmail());
        message.setSubject(subject);
        message.setText("尊敬的用户 " + userBaseInfo.getUserName() + "：\n\n" + content);
        try {
            mailSender.send(message);
        } catch (MailException ex) {
            log.error("[UserServiceImpl-sentMail] error", ex);
        }
    }

}
