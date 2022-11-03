package cc.larryzeta.manager.exception;

import cc.larryzeta.manager.entity.xray.StreamSettingsObject;

public class BizException extends RuntimeException{

    private String code;
    private String msg;

    public BizException() {
        this.code = "9999";
        this.msg = "系统异常";
    }

    public BizException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
