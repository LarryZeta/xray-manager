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
                           @RequestParam("retype-password")String retype,
                           Map<String, Object> map) {
        User exist = userService.getUserByEmail(email);
        if (exist != null) {
            map.put("msg", "The email has been registered.");
            return "register";
        } else if (!password.equals(retype)) {
            map.put("msg", "Inconsistent password.");
            return "register";
        } else {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            userService.registerUser(user);
            return "redirect:/login";
        }
    }

}
