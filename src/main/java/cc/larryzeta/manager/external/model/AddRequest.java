package cc.larryzeta.manager.external.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddRequest implements Serializable {

    private String email;

    private String id;

}
