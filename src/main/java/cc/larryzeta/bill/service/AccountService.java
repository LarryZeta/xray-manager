package cc.larryzeta.bill.service;

import cc.larryzeta.bill.entities.Account;
import cc.larryzeta.bill.entities.Order;

public interface AccountService {

    Account getAccount(Integer uid);

    Integer activeOrder(Order order);

}
