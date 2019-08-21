package cc.larryzeta.bill.entities;

import lombok.Data;


@Data
public class Order {

    private String oid;
    private Integer uid;
    private Integer days;
    private Boolean isActivated;

}
