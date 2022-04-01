package cc.larryzeta.manager.external.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RemoveRequest extends RequestBase {

    private static final long serialVersionUID = 7931636861502692823L;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
