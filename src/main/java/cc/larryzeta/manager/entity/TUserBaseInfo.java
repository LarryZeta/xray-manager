package cc.larryzeta.manager.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_user_base_info
 * @author 
 */
@Data
public class TUserBaseInfo implements Serializable {
    private Integer id;

    private String userName;

    private String phone;

    private String email;

    private String passwd;

    private String userStatus;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;
}