package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.api.account.AccountControllerApi;
import cc.larryzeta.manager.api.model.ResultEntity;
import cc.larryzeta.manager.entity.TXrayAccountInfo;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.service.AccountService;
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
public class AccountController implements AccountControllerApi {

    @Autowired
    private AccountService accountService;

    @RequiresRoles("ADMIN")
    @DeleteMapping(value = "/account/{userId}")
    @ResponseBody
    @Override
    public ResultEntity<String> deleteAccount(@PathVariable Integer userId) {

        log.info("deleteAccount START userId: [{}]", userId);

        ResultEntity<String> resultEntity = new ResultEntity<>();

        try {
            accountService.deleteAccount(userId);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("deleteAccount bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("deleteAccount unknown Exception e", e);
        }

        log.info("deleteAccount END resultEntity: [{}]", resultEntity);

        return resultEntity;
    }

    @GetMapping(value = "/account/{userId}")
    @ResponseBody
    @Override
    public ResultEntity<TXrayAccountInfo> queryAccount(@PathVariable Integer userId) {

        log.info("queryAccount START userId: [{}]", userId);

        ResultEntity<TXrayAccountInfo> resultEntity = new ResultEntity<>();

        try {
            TXrayAccountInfo xrayAccountInfo = accountService.getAccount(userId);
            resultEntity.setData(xrayAccountInfo);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("deleteAccount bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("deleteAccount unknown Exception e", e);
        }

        log.info("queryAccount END resultEntity: [{}]", resultEntity);

        return resultEntity;
    }

    @RequiresRoles("ADMIN")
    @GetMapping(value = "/accounts")
    @ResponseBody
    @Override
    public ResultEntity<List<TXrayAccountInfo>> getAccounts(@RequestBody @Nullable TXrayAccountInfo xrayAccountInfo) {

        log.info("getAccounts START condition xrayAccountInfo: [{}]", JsonUtils.toJSONString(xrayAccountInfo));

        ResultEntity<List<TXrayAccountInfo>> resultEntity = new ResultEntity<>();

        try {
            List<TXrayAccountInfo> xrayAccountInfoList = accountService.getAccounts(xrayAccountInfo);
            resultEntity.setData(xrayAccountInfoList);
        } catch (BizException bizException) {
            resultEntity.setCode(bizException.getCode());
            resultEntity.setMsg(bizException.getMsg());
            log.error("getUsers bizException", bizException);
        } catch (Exception e) {
            resultEntity.setCode(ReturnCodeEnum.EXCEPTION.code);
            resultEntity.setMsg(ReturnCodeEnum.EXCEPTION.msg);
            log.error("getUsers unknown Exception e", e);
        }

        log.info("getAccounts END resultEntity: [{}]", JsonUtils.toJSONString(resultEntity));

        return resultEntity;
    }

}
