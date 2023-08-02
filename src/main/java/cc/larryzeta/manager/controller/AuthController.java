package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.api.auth.AuthControllerApi;
import cc.larryzeta.manager.api.auth.model.AuthRequest;
import cc.larryzeta.manager.api.auth.model.AuthResponse;
import cc.larryzeta.manager.api.model.ResultEntity;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class AuthController implements AuthControllerApi {

    @Autowired
    private AuthService authService;


    @PostMapping(value = "/login")
    @ResponseBody
    @Override
    public ResultEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

        log.info("login START authRequest: [{}]", authRequest);

        ResultEntity<AuthResponse> resultEntity = new ResultEntity<>();

        try {
            AuthResponse authResponse = authService.login(authRequest);
            resultEntity.setData(authResponse);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("login bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("login unknown Exception e", e);
        }

        log.info("login END resultEntity: [{}]", resultEntity);

        return resultEntity;

    }

}
