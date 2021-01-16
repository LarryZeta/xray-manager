package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.entity.User;
import cc.larryzeta.manager.service.AccountService;
import cc.larryzeta.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/users")
    public String toUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @DeleteMapping(value = "/user/{uid}")
    public String DeleteUser(@PathVariable("uid")Integer uid) {
        if (accountService.getAccount(uid) == null) {
            userService.deleteUser(uid);
        }
        return "redirect:/users";
    }

}
