package cc.larryzeta.bill.service;

import cc.larryzeta.bill.entity.Account;
import cc.larryzeta.bill.entity.Order;

import java.util.List;

public interface AccountService {

    Account getAccount(Integer uid);

    List<Account> getAllAccount();

    Integer activeOrder(Order order);

    List<Integer> deleteExpiredAccounts();

    Integer deleteAccountByUid(Integer uid);

    List<Integer> getWarnedAccounts(Integer days);

}
