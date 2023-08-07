package cc.larryzeta.manager.service;

import cc.larryzeta.manager.entity.TXrayAccountInfo;

import java.util.List;

public interface AccountService {

    TXrayAccountInfo getAccount(Integer userId);

    List<TXrayAccountInfo> getAccounts(TXrayAccountInfo xrayAccountInfo);

    void deleteAccount(Integer userId);

    /**
     * 1. get about to expire accounts sentNotice
     * 2. delete expire accounts
     */
    void refreshAccounts();

}
