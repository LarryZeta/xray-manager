package cc.larryzeta.bill.entities;

import lombok.Data;

import java.util.Map;

@Data
public class Client {

    private String id;
    private Integer level;
    private Integer alterId;
    private String email;


    public Client(Map<String, Object> map) {
        this.id = (String) map.get("id");
        this.level = (Integer) map.get("level");
        this.alterId = (Integer) map.get("alterId");
        this.email = (String) map.get("email");
    }

}
