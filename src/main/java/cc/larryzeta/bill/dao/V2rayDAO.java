package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entities.Client;
import cc.larryzeta.bill.entities.V2rayConfig;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class V2rayDAO {

    private static final String path = "C:/Users/zly/Desktop/config.json";

    public V2rayConfig getV2rayConfig() throws FileNotFoundException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();
        return gson.fromJson(bufferedReader, V2rayConfig.class);

    }

    public List<Client> getAllClients() throws FileNotFoundException {

        V2rayConfig v2rayConfig = getV2rayConfig();
        Map<String, List<Map<String, Object>>> settings = (Map<String, List<Map<String, Object>>>) v2rayConfig.getInbounds().get(0).get("settings");
        List<Map<String, Object>> list = settings.get("clients");
        List<Client> clients = new LinkedList<>();
        for (Map<String, Object> map : list) {
            clients.add(new Client(map));
        }

        return clients;

    }

}
