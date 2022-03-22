package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {

        try {
            userService.login(email, password, map, session);
            return "redirect:/service";
        } catch (Exception e) {
            log.warn("[LoginController-login] Exception", e);
        }

        return "login";

    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        userService.logout(session);
        return "redirect:/login";
    }

}
