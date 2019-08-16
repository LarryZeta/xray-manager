package cc.larryzeta.bill.entities;

import com.google.gson.Gson;

public class Client {

    private String id;
    private Integer level;
    private Integer alterId;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAlterId() {
        return alterId;
    }

    public void setAlterId(Integer alterId) {
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
}
