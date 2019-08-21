package cc.larryzeta.bill.entities;

import lombok.Data;

import java.sql.Date;

@Data
public class Account {

    private String aid;
    private Integer uid;
    private Date activationDate;
    private Date expireDate;

}
