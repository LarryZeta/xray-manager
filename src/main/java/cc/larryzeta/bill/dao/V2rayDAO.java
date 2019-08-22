package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entities.Client;
import cc.larryzeta.bill.entities.V2rayConfig;
import cc.larryzeta.bill.util.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class V2rayDAO {

    private static final String path = "/etc/v2ray/config.json";

    private Gson getGson = new GsonBuilder().registerTypeAdapter(new TypeToken<Map<String, Object>>(){
    }.getType(), new GsonTypeAdapter()).create();

    private Gson writeGson = new Gson();

    private V2rayConfig v2rayConfig = getV2rayConfig();

    private Map<String, List<Map<String, Object>>> settings = (Map<String, List<Map<String, Object>>>) v2rayConfig.getInbounds().get(0).get("settings");

    public V2rayConfig getV2rayConfig() {

        V2rayConfig v2rayConfig = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            v2rayConfig = getGson.fromJson(bufferedReader, V2rayConfig.class);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return v2rayConfig;

    }

    public Boolean writeV2rayConfig(V2rayConfig v2rayConfig) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(writeGson.toJson(v2rayConfig));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    // TODO Map 转 Client 方式改为通用的或不转，待优化
    public List<Client> getAllClients() {

        List<Map<String, Object>> list = settings.get("clients");
        List<Client> clients = new LinkedList<>();
        for (Map<String, Object> map : list) {
            clients.add(new Client(map));
        }

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
