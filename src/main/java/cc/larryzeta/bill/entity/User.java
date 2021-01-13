package cc.larryzeta.bill.entity;

import lombok.Data;

@Data
public class User {

    private Integer uid;
    private String username;
    private String email;
    private String password;
    private Boolean isAdmin;

}
