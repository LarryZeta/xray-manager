package cc.larryzeta.manager;

import cc.larryzeta.manager.biz.XrayBiz;
import cc.larryzeta.manager.entity.TXrayServerInfo;
import cc.larryzeta.manager.external.model.Client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class XrayManagerTests {

    @Autowired
    private XrayBiz xrayBiz;

    @Test
    public void contextLoads() {
        Client client = new Client();
        client.setId("tid");
        client.setEmail("t@em");
        List<Client> clientList = Collections.singletonList(client);

        TXrayServerInfo xrayServerInfo = new TXrayServerInfo();
        xrayServerInfo.setAddress("http://localhost:8000");
        xrayServerInfo.setToken("");

        xrayBiz.syncClient(clientList, xrayServerInfo);

    }

}
