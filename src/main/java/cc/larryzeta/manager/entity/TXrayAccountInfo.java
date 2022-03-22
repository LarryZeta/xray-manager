package cc.larryzeta.manager.entity;

import java.util.Date;
import lombok.Data;

@Data
public class TXrayAccountInfo {
    private String id;

    private String userId;

    private String uuid;

    private String status;

    private Date effectiveTime;

    private Date expireTime;

    private Date createTime;

    private Date updateTime;

    private String remark;
}