package cc.larryzeta.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("cc.larryzeta.manager.mapper")
public class XrayManager {

    public static void main(String[] args) {
        SpringApplication.run(XrayManager.class, args);
    }

}
