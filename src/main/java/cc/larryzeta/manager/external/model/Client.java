package cc.larryzeta.manager.external.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Client implements Serializable {

    private static final long serialVersionUID = -3343902941335697481L;

    private String id;

    private String email;

}