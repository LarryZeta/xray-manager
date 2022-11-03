package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("retype-password")String retype,
                           Map<String, Object> map) {
        try {
            userService.register(username, email, password, retype);
            return "redirect:/login";
        } catch (BizException bizException) {
            log.warn("发生业务异常", bizException);
            map.put("msg", bizException.getMsg());
            return "register";
        } catch (Exception e) {
            log.warn("发生其他异常", e);
            map.put("msg", "系统异常请稍后再试");
            return "register";
        }
    }

}
