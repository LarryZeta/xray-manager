package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.api.model.ResultEntity;
import cc.larryzeta.manager.api.user.UserControllerApi;
import cc.larryzeta.manager.api.user.model.RegisterRequest;
import cc.larryzeta.manager.api.user.model.UpdatePassWordRequest;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.service.UserService;
import cc.larryzeta.manager.util.JsonUtils;
import io.micrometer.core.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class UserController implements UserControllerApi {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    @ResponseBody
    @Override
    public ResultEntity<String> register(@RequestBody RegisterRequest registerRequest) {

        log.info("register START RegisterRequest: [{}]", JsonUtils.toJSONString(registerRequest));

        ResultEntity<String> resultEntity = new ResultEntity<>();
        try {
            userService.register(registerRequest);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("register bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("register unknown Exception e", e);
        }

        log.info("register END resultEntity: [{}]", resultEntity);

        return resultEntity;
    }

    @DeleteMapping(value = "/user/{email}")
    @Override
    public ResultEntity<String> deleteUser(@PathVariable String email) {

        log.info("deleteUser START email: {}", email);

        ResultEntity<String> resultEntity = new ResultEntity<>();

        try {
            userService.deleteUser(email);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("deleteUser bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("deleteUser unknown Exception e", e);
        }


        log.info("deleteUser END resultEntity: {}", JsonUtils.toJSONString(resultEntity));

        return resultEntity;
    }

    @RequiresRoles("ADMIN")
    @GetMapping(value = "/users")
    @ResponseBody
    @Override
    public ResultEntity<List<TUserBaseInfo>> getUsers(@RequestBody @Nullable TUserBaseInfo userBaseInfo) {

        log.info("getUsers START condition userBaseInfo: [{}]", JsonUtils.toJSONString(userBaseInfo));

        List<TUserBaseInfo> users = userService.getUser(userBaseInfo);
        ResultEntity<List<TUserBaseInfo>> resultEntity = new ResultEntity<>();
        resultEntity.setData(users);

        log.info("getUsers END resultEntity: [{}]", JsonUtils.toJSONString(resultEntity));

        return resultEntity;
    }

    @PutMapping(value = "/user/{email}")
    @ResponseBody
    @Override
    public ResultEntity<TUserBaseInfo> userPassword(@PathVariable String email, @RequestBody UpdatePassWordRequest updatePassWordRequest) {

        log.info("userPassword START email: [{}] UpdatePassWordRequest: [{}]", email, JsonUtils.toJSONString(updatePassWordRequest));

        ResultEntity<TUserBaseInfo> resultEntity = new ResultEntity<>();

        try {
            TUserBaseInfo userBaseInfo = userService.updatePassWord(email, updatePassWordRequest);
            resultEntity.setData(userBaseInfo);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("userPassword bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("userPassword unknown Exception e", e);
        }

        log.info("userPassword END resultEntity: [{}]", resultEntity);

        return resultEntity;
    }


    @GetMapping(value = "/user/{email}")
    @ResponseBody
    @Override
    public ResultEntity<TUserBaseInfo> getUser(@PathVariable String email) {

        log.info("getUser START email: {}", email);

        TUserBaseInfo query = new TUserBaseInfo();
        query.setEmail(email);
        List<TUserBaseInfo> users = userService.getUser(query);
        ResultEntity<TUserBaseInfo> resultEntity = new ResultEntity<>();
        resultEntity.setData(users.get(0));

        log.info("getUser END resultEntity: {}", JsonUtils.toJSONString(resultEntity));

        return resultEntity;
    }

}
