package cc.larryzeta.manager.service;


import cc.larryzeta.manager.api.auth.model.AuthRequest;
import cc.larryzeta.manager.api.auth.model.AuthResponse;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AuthService {

    AuthResponse login(AuthRequest authRequest);

}
