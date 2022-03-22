package cc.larryzeta.manager.api.user;

import cc.larryzeta.manager.api.model.ResultEntity;
import feign.Headers;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface UserControllerApi {

    ResultEntity<Object> AddUser(Object o);

    ResultEntity<Object> deleteUser(Object o);

    ResultEntity<Object> queryUser(Object o);

    ResultEntity<Object> updateUser(Object o);

}
