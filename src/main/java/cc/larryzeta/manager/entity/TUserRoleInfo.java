package cc.larryzeta.manager.entity;

import java.util.Date;
import lombok.Data;

@Data
public class TUserRoleInfo {
    private String id;

    private String roleCode;

    private String roleName;

    /**
    * 状态 INIT-初始化；VALID-有效；DELETE-删除；
    */
    private String status;

    private Date createTime;

    private Date updateTime;

    private String remark;
}