package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.dao.AccountDAO;
import cc.larryzeta.manager.dao.OrderDAO;
import cc.larryzeta.manager.entity.Account;
import cc.larryzeta.manager.entity.Order;
import cc.larryzeta.manager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Account getAccount(Integer uid) {
        return accountDAO.getAccountByUid(uid);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountDAO.getAllAccount();
    }

    @Override
    public Integer activeOrder(Order order) {

        Account account = accountDAO.getAccountByUid(order.getUid());

        if (account == null) {
            account = new Account();
            account.setAid(UUID.randomUUID().toString());
            account.setUid(order.getUid());
            Date currentDate = new Date(System.currentTimeMillis());
            account.setActivationDate(currentDate);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(currentDate);
            calendar.add(calendar.DATE, order.getDays());
            account.setExpireDate(new Date(calendar.getTimeInMillis()));
            orderDAO.setActiveated(order.getOid());
            return accountDAO.addAccount(account);
        } else {
            Date expireDate = account.getExpireDate();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(expireDate);
            calendar.add(calendar.DATE, order.getDays());
            account.setExpireDate(new Date(calendar.getTimeInMillis()));
            orderDAO.setActiveated(order.getOid());
            return accountDAO.updateAccount(account);
        }

    }

    @Override
    public List<Integer> deleteExpiredAccounts() {

        Date currentDate = new Date(System.currentTimeMillis());
        List<Integer> list = accountDAO.getExpiredAccounts(currentDate);
        accountDAO.deleteExpiredAccounts(currentDate);
        return list;

    }

    @Override
    public Integer deleteAccountByUid(Integer uid) {
        return accountDAO.deleteAccountByUid(uid);
    }

    // 当前日期 + days 超过过期时间的账号
    @Override
    public List<Integer> getWarnedAccounts(Integer days) {
        Date currentDate = new Date(System.currentTimeMillis());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        calendar.add(calendar.DATE, days);
        Date warnedDate = new Date(calendar.getTimeInMillis());
        return accountDAO.getExpiredAccounts(warnedDate);
    }

}
