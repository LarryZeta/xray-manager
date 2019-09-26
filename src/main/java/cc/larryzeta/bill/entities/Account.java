package cc.larryzeta.bill.entities;

import lombok.Data;

import java.sql.Date;

@Data
public class Account extends User{

    private String aid;
    private Date activationDate;
    private Date expireDate;

}
