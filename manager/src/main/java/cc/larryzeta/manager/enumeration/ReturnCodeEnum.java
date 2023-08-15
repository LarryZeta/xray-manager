package cc.larryzeta.manager.enumeration;

/**
 * ProjectName xray-manager
 * EnumName ReturnCodeEnum
 * Date 3/1/2022 15:09
 *
 * @author Larry
 * @description
 */
public enum ReturnCodeEnum {

    /**
     * 成功
     */
    SUCCESS("0000", "成功"),
    EXCEPTION("9999", "服务器代码发生异常,请联系管理员"),

    SQL_EXCEPTION("1000", "数据库访问异常"),

    PARAM_EXCEPTION("2000", "参数错误");

    public final String code;

    public final String msg;

    ReturnCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
