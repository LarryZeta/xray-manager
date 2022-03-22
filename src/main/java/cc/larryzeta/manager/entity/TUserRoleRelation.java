package cc.larryzeta.manager.entity;

import java.util.Date;
import lombok.Data;

@Data
public class TUserRoleRelation {
    private String id;

    private String userId;

    private String roleCode;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String remark;
}