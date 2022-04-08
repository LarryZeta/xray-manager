package cc.larryzeta.manager.api.user;

import cc.larryzeta.manager.api.model.ResultEntity;
import feign.Headers;
import feign.RequestLine;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface UserControllerApi {

    @RequestLine("POST /user")
    ResultEntity<Object> AddUser(Object o);

    @RequestLine("GET /user")
    ResultEntity<Object> queryUser(Object o);

}
