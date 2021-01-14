package cc.larryzeta.bill;

import cc.larryzeta.bill.biz.XrayBiz;
import cc.larryzeta.bill.dao.XrayDAO;
import cc.larryzeta.bill.entity.XrayConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillApplicationTests {

    @Autowired
    XrayDAO xrayDAO;
    private static final ObjectMapper MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Autowired
    XrayBiz xrayBiz;

    @Test
    public void contextLoads() {

//        try {
//            XrayConfig xrayConfig = xrayDAO.getXrayConfig();
//            System.out.println(MAPPER.writeValueAsString(xrayConfig));
//            System.out.println(xrayConfig.getInbounds().get(0).getClass());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        System.out.println(xrayBiz.findClient("981558469@qq.com"));
    }

}
