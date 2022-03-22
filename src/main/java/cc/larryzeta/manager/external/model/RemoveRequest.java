package cc.larryzeta.manager.external.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RemoveRequest implements Serializable {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
