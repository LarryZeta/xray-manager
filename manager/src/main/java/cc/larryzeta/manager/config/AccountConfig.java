package cc.larryzeta.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AccountConfig {

    @Value("${account.notice.advance:7}")
    private Integer noticeBeforeDays;

    public Integer getNoticeBeforeDays() {
        return noticeBeforeDays;
    }

}
