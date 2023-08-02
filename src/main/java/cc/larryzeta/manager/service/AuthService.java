package cc.larryzeta.manager.service;


import cc.larryzeta.manager.api.auth.model.AuthRequest;
import cc.larryzeta.manager.api.auth.model.AuthResponse;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AuthService {

    void login(String email, String password, Map<String, Object> map, HttpSession session);

    AuthResponse login(AuthRequest authRequest);

    void logout(HttpSession session);

}
