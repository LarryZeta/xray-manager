package cc.larryzeta.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XrayManager {

    public static void main(String[] args) {
        SpringApplication.run(XrayManager.class, args);
    }

}
