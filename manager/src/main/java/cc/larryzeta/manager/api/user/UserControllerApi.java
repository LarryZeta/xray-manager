package cc.larryzeta.manager.api.user;

import cc.larryzeta.manager.api.model.ResultEntity;
import cc.larryzeta.manager.api.user.model.RegisterRequest;
import cc.larryzeta.manager.api.user.model.UpdatePassWordRequest;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface UserControllerApi {

    @RequestLine("POST /user/register")
    ResultEntity<String> register(RegisterRequest request);

    @RequestLine("DELETE /user/{email}")
    ResultEntity<String> deleteUser(String email);

    @RequestLine("GET /user/{email}")
    ResultEntity<TUserBaseInfo> getUser(String email);

    @RequestLine("GET /users")
    ResultEntity<List<TUserBaseInfo>> getUsers(TUserBaseInfo userBaseInfo);

    @RequestLine("PUT /user/{email}")
    ResultEntity<TUserBaseInfo> userPassword(String email, UpdatePassWordRequest updatePassWordRequest);

}
