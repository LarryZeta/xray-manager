package cc.larryzeta.manager.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultEntity<T> implements Serializable {

    private String code;

    private String msg;

}
