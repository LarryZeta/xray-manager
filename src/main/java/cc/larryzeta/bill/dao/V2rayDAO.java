package cc.larryzeta.bill.dao;

import cc.larryzeta.bill.entities.V2rayConfig;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class V2rayDAO {

    private static final String path = "C:/Users/zhanglingyu/Desktop/test/config.json";

    public V2rayConfig getV2rayConfig() throws FileNotFoundException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();
        return gson.fromJson(bufferedReader, V2rayConfig.class);

    }

}
