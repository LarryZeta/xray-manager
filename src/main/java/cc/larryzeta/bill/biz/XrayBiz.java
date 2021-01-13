package cc.larryzeta.bill.biz;

import cc.larryzeta.bill.dao.XrayDAO;
import cc.larryzeta.bill.entity.Client;
import cc.larryzeta.bill.entity.XrayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// TODO
@Service
public class XrayBiz {

    @Autowired
    private static XrayDAO xrayDAO;

    public List<Client> getAllClients() {

        XrayConfig xrayConfig = xrayDAO.getXrayConfig();

        List<Client> clients = new LinkedList<>();


        return clients;

    }

    public Boolean findClient(String email) {

//        List<Map<String, Object>> list = settings.get("clients");
//        for (Map<String, Object> map : list) {
//            if (email.equals(map.get("email"))) {
//                return true;
//            }
//        }

        return false;
    }

    public Boolean addClient(String email, String uuid) {


//        Map<String, Object> map = new HashMap<>();
//        map.put("id", uuid);
//        map.put("level", 1);
//        map.put("alterId", 16);
//        map.put("email", email);
//        settings.get("clients").add(map);
//        System.out.println(writeGson.toJson(v2rayConfig));
//        writeV2rayConfig(v2rayConfig);
        return true;

    }

    public Boolean deleteClient(String email) {

//        List<Map<String, Object>> clients = settings.get("clients");
//        for (int i = 0; i < clients.size(); i ++) {
//            Map<String, Object> client = clients.get(i);
//            if (client.get("email") != null && email.equals(client.get("email"))) {
//                clients.remove(client);
//                i --;
//            }
//        }
//        System.out.println(writeGson.toJson(v2rayConfig));
//        writeV2rayConfig(v2rayConfig);
        return true;

    }

}
