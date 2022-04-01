package cc.larryzeta.manager.external.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {

    private static final long serialVersionUID = -357100322598020858L;

    private String code;

    private String msg;

}
