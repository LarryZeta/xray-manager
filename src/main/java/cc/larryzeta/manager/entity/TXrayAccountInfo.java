package cc.larryzeta.manager.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_xray_account_info
 * @author 
 */
@Data
public class TXrayAccountInfo implements Serializable {
    private Integer id;

    private Integer userId;

    private String uuid;

    private String accountStatus;

    private Date effectiveTime;

    private Date expireTime;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;
}