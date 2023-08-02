package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.entity.Account;
import cc.larryzeta.manager.service.AccountService;
import cc.larryzeta.manager.service.NoticeService;
import cc.larryzeta.manager.service.UserService;
import cc.larryzeta.manager.service.XrayService;
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
    private XrayService xrayService;
    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

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

    @DeleteMapping(value = "/account/{uid}")
    public String deleteAccount(@PathVariable("uid") Integer uid) {
        accountService.deleteAccountByUid(uid);
        noticeService.sentMail(uid, "账号删除提醒", "您的账号已被删除。");
        xrayService.deleteClient(uid);
        return "redirect:/accounts";
    }

}
