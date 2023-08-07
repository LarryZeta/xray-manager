package cc.larryzeta.manager.enumeration;

public enum StatusEnum {

    VALID("VALID", "有效"),

    ACTIVATED("ACTIVATED", "已激活"),

    INVALID("INVALID", "失效"),

    DELETED("DELETED", "已经删除");

    public String code;

    public String msg;

    StatusEnum(String code, String msg) {

        this.code = code;
        this.msg = msg;

    }

}
