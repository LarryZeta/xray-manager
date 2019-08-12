package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.entities.User;
import cc.larryzeta.bill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           Map<String, Object> map) {
        User user = userService.getUserByEmail(email);
        if (user == null) {

            return "redirect:/login";
        } else {
            map.put("msg", "The email has been registered.");
            return "register";
        }
    }

}
