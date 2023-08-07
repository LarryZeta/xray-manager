package cc.larryzeta.manager.service;

import cc.larryzeta.manager.api.user.model.RegisterRequest;
import cc.larryzeta.manager.api.user.model.UpdatePassWordRequest;
import cc.larryzeta.manager.entity.TUserBaseInfo;

import java.util.List;

public interface UserService {

    void register(RegisterRequest registerRequest);

    void deleteUser(String email);

    List<String> getRoleList(String username);

    List<String> getPermissions(String username);

    List<TUserBaseInfo> getUsers(TUserBaseInfo userBaseInfo);

    TUserBaseInfo getUser(String email);

    TUserBaseInfo updatePassWord(String email, UpdatePassWordRequest updatePassWordRequest);

}
