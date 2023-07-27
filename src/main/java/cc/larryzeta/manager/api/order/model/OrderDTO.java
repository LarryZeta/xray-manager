package cc.larryzeta.manager.api.order.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDTO implements Serializable {

    private Integer userId;

    private String orderId;

    private String orderPrice;

    private String orderStatus;

    private Date activeTime;

    private Date createTime;

    private Date updateTime;

}
