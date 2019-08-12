package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.entities.User;
import cc.larryzeta.bill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
        User user = userService.getUserByEmail(email);
        if (!StringUtils.isEmpty(email) && user != null && user.getPassword().equals(password)) {
            session.setAttribute("loginUser", user.getUsername());
            return "redirect:/index.html";
        } else {
            map.put("msg", "Invalid email or password.");
            return "login";
        }
    }
}
