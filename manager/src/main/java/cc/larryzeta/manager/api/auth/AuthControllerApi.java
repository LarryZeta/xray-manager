package cc.larryzeta.manager.api.auth;

import cc.larryzeta.manager.api.auth.model.AuthRequest;
import cc.larryzeta.manager.api.auth.model.AuthResponse;
import cc.larryzeta.manager.api.model.ResultEntity;
import feign.Headers;
import feign.RequestLine;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface AuthControllerApi {

    @RequestLine("POST /login")
    ResultEntity<AuthResponse> login(AuthRequest authRequest);

}
