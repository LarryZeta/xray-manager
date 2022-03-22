package cc.larryzeta.manager.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
* 服务计划信息
*/
@Data
public class TPlanInfo {
    private String id;

    private String planName;

    private BigDecimal planPeriod;

    private BigDecimal planPrice;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String remark;
}