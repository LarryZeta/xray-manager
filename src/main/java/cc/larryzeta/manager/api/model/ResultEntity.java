package cc.larryzeta.manager.api.model;

import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultEntity<T> implements Serializable {

    private String code = ReturnCodeEnum.SUCCESS.code;
    private String msg = ReturnCodeEnum.SUCCESS.msg;

    private T data;

}
