package cc.larryzeta.manager.service;

import cc.larryzeta.manager.entity.Account;
import cc.larryzeta.manager.entity.Order;

import java.util.List;

public interface AccountService {

    Account getAccount(Integer uid);

    List<Account> getAllAccount();

    Integer activeOrder(Order order);

    List<Integer> deleteExpiredAccounts();

    Integer deleteAccountByUid(Integer uid);

    List<Integer> getWarnedAccounts(Integer days);

}
