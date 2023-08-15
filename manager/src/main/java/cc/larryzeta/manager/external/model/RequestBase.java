package cc.larryzeta.manager.external.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestBase implements Serializable {

    private static final long serialVersionUID = -2152955164707481829L;

    private String accessKey;

    private String secretKey;

}
