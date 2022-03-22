package cc.larryzeta.manager.entity;

import java.util.Date;
import lombok.Data;

/**
* user base info
*/
@Data
public class TUserBaseInfo {
    /**
    * primary key
    */
    private String id;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 密码
    */
    private String passwd;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String remark;
}