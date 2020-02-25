package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if (userService.login(email, password, map, session)) {
            return "redirect:/service";
        } else {
            return "login";
        }
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        userService.logout(session);
        return "redirect:/login";
    }

}
