package cc.larryzeta.manager.external.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestBase implements Serializable {

    private static final long serialVersionUID = -2152955164707481829L;

    private String token;

    private String otp;

}
