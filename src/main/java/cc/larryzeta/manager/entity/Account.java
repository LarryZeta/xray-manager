package cc.larryzeta.manager.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Account extends User {

    private String aid;
    private Date activationDate;
    private Date expireDate;

}
