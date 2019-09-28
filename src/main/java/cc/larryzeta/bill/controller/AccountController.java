package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.entities.Account;
import cc.larryzeta.bill.service.AccountService;
import cc.larryzeta.bill.service.UserService;
import cc.larryzeta.bill.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private V2rayService v2rayService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/account")
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

    @GetMapping(value = "/accounts")
    public String toAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccount());
        return "accounts";
    }

    @DeleteMapping(value = "/account/{aid}")
    public String deleteAccount(@PathVariable("aid") String aid) {
        Integer uid = accountService.deleteAccount(aid);
        userService.sentMail(uid, "账号删除提醒", "您的账号已被删除。");
        v2rayService.deleteClient(uid);
        return "redirect:/accounts";
    }

}
