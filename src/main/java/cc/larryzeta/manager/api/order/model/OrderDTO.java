package cc.larryzeta.manager.api.order.model;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDTO implements Serializable {

    @NotBlank
    private Integer userId;

    private String orderId;

    @NotBlank
    private String orderPrice;

    @NotBlank
    private String orderDays;

    private String orderStatus;

    private Date activeTime;

    private Date createTime;

    private Date updateTime;

}
