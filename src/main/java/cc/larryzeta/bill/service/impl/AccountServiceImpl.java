package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.dao.AccountDAO;
import cc.larryzeta.bill.entities.Account;
import cc.larryzeta.bill.entities.Order;
import cc.larryzeta.bill.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO accountDAO;

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
            order.setIsActivated(true);
            return accountDAO.addAccount(account);
        } else {
            Date expireDate = account.getExpireDate();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(expireDate);
            calendar.add(calendar.DATE, order.getDays());
            account.setExpireDate(new Date(calendar.getTimeInMillis()));
            order.setIsActivated(true);
            return accountDAO.updateAccount(account);
        }

    }

}
