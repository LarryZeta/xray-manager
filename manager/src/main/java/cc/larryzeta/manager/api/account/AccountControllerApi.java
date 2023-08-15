package cc.larryzeta.manager.api.account;

import cc.larryzeta.manager.api.account.model.AddAccountRequest;
import cc.larryzeta.manager.api.model.ResultEntity;
import cc.larryzeta.manager.entity.TXrayAccountInfo;
import feign.RequestLine;

import java.util.List;

public interface AccountControllerApi {

    @RequestLine("DELETE /account/{userId}")
    ResultEntity<String> deleteAccount(Integer userId);

    @RequestLine("GET /account/{userId}")
    ResultEntity<TXrayAccountInfo> queryAccount(Integer userId);

    @RequestLine("GET /accounts")
    ResultEntity<List<TXrayAccountInfo>> getAccounts(TXrayAccountInfo xrayAccountInfo);

    @RequestLine("GET /accounts/sync")
    ResultEntity<String> syncAccount();

}
