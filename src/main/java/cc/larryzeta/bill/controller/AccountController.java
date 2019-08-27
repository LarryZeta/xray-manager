package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.entities.Account;
import cc.larryzeta.bill.entities.User;
import cc.larryzeta.bill.service.AccountService;
import cc.larryzeta.bill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/account")
    public String toAccount(HttpSession session, Model model) {
        Integer uid = (Integer) session.getAttribute("uid");
        Account account = accountService.getAccount(uid);
        if (account != null) {
            model.addAttribute("account", account);
            return "account";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping(value = "/users")
    public String toUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

}
