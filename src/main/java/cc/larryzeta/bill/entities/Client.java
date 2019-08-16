package cc.larryzeta.bill.entities;

import com.google.gson.Gson;

import java.util.Map;

public class Client {

    private String id;
    private Double level;
    private Double alterId;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public Double getAlterId() {
        return alterId;
    }

    public void setAlterId(Double alterId) {
        this.alterId = alterId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
