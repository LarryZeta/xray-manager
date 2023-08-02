package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
//@Controller
public class RegisterController {

//    @Autowired
//    private UserService userService;

//    @PostMapping(value = "/user/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("retype-password")String retype,
                           Map<String, Object> map) {

        try {
//            userService.register(username, email, password,retype , map);
            return "redirect:/login";
        } catch (Exception e) {
            log.warn("[RegisterController-register] Exception", e);
        }

        return "register";
    }

}
