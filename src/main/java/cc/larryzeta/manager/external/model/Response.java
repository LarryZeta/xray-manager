package cc.larryzeta.manager.external.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {

    private String code;

    private String msg;

}
