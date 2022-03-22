package cc.larryzeta.manager.exception;

import cc.larryzeta.manager.enumeration.ReturnCodeEnum;

/**
 * ProjectName xray-manager
 * ClassName ReturnException
 * Date 3/1/2022 14:59
 *
 * @author Larry
 * @description
 */
public class ReturnException extends RuntimeException {

    private static final long serialVersionUID = -4586497711041741635L;

    private static String TIP_HEAD = "业务处理出现异常";
    private String code = ReturnCodeEnum.SUCCESS.code;
    private String msg = ReturnCodeEnum.SUCCESS.msg;

    public ReturnException() {
        super(TIP_HEAD);
        this.code = ReturnCodeEnum.EXCEPTION.code;
        this.msg = ReturnCodeEnum.EXCEPTION.msg;
    }

    public ReturnException( String message ) {
        super(TIP_HEAD + " - " + message);
        this.code = ReturnCodeEnum.EXCEPTION.code;
        this.msg = ReturnCodeEnum.EXCEPTION.msg;
    }

    public ReturnException( String code, String msg ) {
        super(TIP_HEAD + " - " + code + ":" + msg);
        this.code = code;
        this.msg = msg;
    }
    

    public ReturnException( ReturnCodeEnum returnCodeEnum ) {
        this(returnCodeEnum.code, returnCodeEnum.msg);
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
