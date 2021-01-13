package cc.larryzeta.bill;

import cc.larryzeta.bill.dao.XrayDAO;
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


    @Test
    public void contextLoads() {

        try {
            System.out.println(MAPPER.writeValueAsString(xrayDAO.getXrayConfig()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
