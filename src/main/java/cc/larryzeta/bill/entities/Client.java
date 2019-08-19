package cc.larryzeta.bill.entities;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Map;

@Data
public class Client {

    private String id;
    private Double level;
    private Double alterId;
    private String email;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public Client(Map<String, Object> map) {
        this.id = (String) map.get("id");
        this.level = (Double) map.get("level");
        this.alterId = (Double) map.get("alterId");
        this.email = (String) map.get("email");
    }

}
