package cc.larryzeta.manager.api.account;

import cc.larryzeta.manager.api.model.ResultEntity;

public interface AccountControllerApi {

    ResultEntity<Object> AddAccount(Object o);

    ResultEntity<Object> deleteAccount(Object o);

    ResultEntity<Object> queryAccount(Object o);

    ResultEntity<Object> updateAccount(Object o);

    ResultEntity<Object> syncAccount(Object o);

}
