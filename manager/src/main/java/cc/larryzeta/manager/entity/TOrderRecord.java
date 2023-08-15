package cc.larryzeta.manager.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_order_record
 * @author 
 */
@Data
public class TOrderRecord implements Serializable {
    private Integer id;

    private Integer userId;

    private String orderId;

    private String orderPrice;

    private String orderDays;

    private String orderStatus;

    private Date activeTime;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;
}