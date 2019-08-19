package cc.larryzeta.bill.entities;

import com.google.gson.Gson;
import lombok.Data;

import java.sql.Date;

@Data
public class Order {

    private Integer uid;
    private Boolean isActivated;
    private Date activationDate;
    private Date expireDate;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
