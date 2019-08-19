package cc.larryzeta.bill.entities;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Map;

@Data
public class Client {

    private String id;
    private Integer level;
    private Integer alterId;
    private String email;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public Client(Map<String, Object> map) {
        this.id = (String) map.get("id");
        Double level = (Double) map.get("level");
        this.level = level.intValue();
        Double alterId = (Double) map.get("alterId");
        this.alterId = alterId.intValue();
        this.email = (String) map.get("email");
    }

}
