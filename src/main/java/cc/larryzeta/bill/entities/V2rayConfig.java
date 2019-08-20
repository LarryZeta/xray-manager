package cc.larryzeta.bill.entities;

import com.google.gson.Gson;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class V2rayConfig {

    private Map<String, String> log;
    private List<Map<String, Object>> inbounds;
    private List<Object> outbounds;
    private Map<String, Object> dns;
    private Map<String, Object> routing;
    private Map<String, Object> transport;


}
