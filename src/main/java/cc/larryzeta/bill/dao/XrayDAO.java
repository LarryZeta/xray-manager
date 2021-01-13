package cc.larryzeta.bill.dao;


import cc.larryzeta.bill.entities.Client;
import cc.larryzeta.bill.entities.V2rayConfig;
import cc.larryzeta.bill.util.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class XrayDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(XrayDAO.class);

    @Value("xray.config-path")
    private static String path;


    private Map<String, Object> getXrayConfig() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            Map<String, Object> configMap = JSONUtil.getMap(bufferedReader.toString());
            bufferedReader.close();
            return configMap;
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error("JSON解析错误", jsonProcessingException);
        } catch (IOException e) {
            LOGGER.error("获取配置文件I/O错误", e);
        }
        return null;

    }

    private void writeXrayConfig(Map<String, Object> configMap) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(JSONUtil.toJsonString(configMap));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // TODO Map 转 Client 方式改为通用的或不转，待优化
    public List<Client> getAllClients() {

        Map<String, Object> configMap = getXrayConfig();
        List<Map<String, Object>> inbounds = (List<Map<String, Object>>) configMap.get("inbounds");
        List<Map<String, Object>> vless_list = (List<Map<String, Object>>) inbounds.get(0).get("settings").get("clients");
        List<Map<String, Object>> vmess_list = (List<Map<String, Object>>) inbounds.get(1).get("settings").get("clients");
        List<Client> clients = new LinkedList<>();


        return clients;

    }

    public Boolean findClient(String email) {

        List<Map<String, Object>> list = settings.get("clients");
        for (Map<String, Object> map : list) {
            if (email.equals(map.get("email"))) {
                return true;
            }
        }

        return false;
    }

    public Boolean addClient(String email, String uuid) {


        Map<String, Object> map = new HashMap<>();
        map.put("id", uuid);
        map.put("level", 1);
        map.put("alterId", 16);
        map.put("email", email);
        settings.get("clients").add(map);
        System.out.println(writeGson.toJson(v2rayConfig));
        writeV2rayConfig(v2rayConfig);
        return true;

    }

    public Boolean deleteClient(String email) {

        List<Map<String, Object>> clients = settings.get("clients");
        for (int i = 0; i < clients.size(); i ++) {
            Map<String, Object> client = clients.get(i);
            if (client.get("email") != null && email.equals(client.get("email"))) {
                clients.remove(client);
                i --;
            }
        }
        System.out.println(writeGson.toJson(v2rayConfig));
        writeV2rayConfig(v2rayConfig);
        return true;

    }

}
