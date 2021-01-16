package cc.larryzeta.manager.entity;

import lombok.Data;


@Data
public class Order extends User{

    private String oid;
    private Integer days;
    private Boolean isActivated;

}
