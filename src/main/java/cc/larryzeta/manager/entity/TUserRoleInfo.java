package cc.larryzeta.manager.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_user_role_info
 * @author 
 */
@Data
public class TUserRoleInfo implements Serializable {
    private Integer id;

    private Integer userId;

    private String roleCode;

    private String roleName;

    private String roleStatus;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;
}