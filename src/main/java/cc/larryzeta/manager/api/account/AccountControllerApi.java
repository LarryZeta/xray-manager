package cc.larryzeta.manager.api.account;

import cc.larryzeta.manager.api.account.model.AddAccountRequest;
import cc.larryzeta.manager.api.model.ResultEntity;
import feign.RequestLine;

public interface AccountControllerApi {

    @RequestLine("POST /account/add")
    ResultEntity<String> AddAccount(AddAccountRequest request);

    ResultEntity<String> deleteAccount(Object o);

    ResultEntity<Object> queryAccount(Object o);

    ResultEntity<Object> updateAccount(Object o);

    ResultEntity<Object> syncAccount(Object o);

}
