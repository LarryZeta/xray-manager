package cc.larryzeta.manager.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class TOrderRecord {

    private String id;

    private String userId;

    private String orderId;

    private BigDecimal servicePrice;

    private String status;

    private Date createTime;

    private Date updateTime;

    private Date achiveTime;

    private String remark;
}