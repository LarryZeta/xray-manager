package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.entity.Account;
import cc.larryzeta.manager.entity.Order;
import cc.larryzeta.manager.service.AccountService;
import cc.larryzeta.manager.service.OrderService;
import cc.larryzeta.manager.service.XrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private XrayService xrayService;

    @GetMapping(value = "/orders")
    public String toOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @GetMapping(value = "/user/orders")
    public String toUserOrders(HttpSession session, Model model) {
        Integer uid = (Integer) session.getAttribute("uid");
        model.addAttribute("orders", orderService.getOrdersByUid(uid));
        return "orders";
    }

    @GetMapping(value = "/order/{oid}")
    public String activeOrder(@PathVariable("oid") String oid) {
        Order order = orderService.getOrderByOid(oid);
        Integer uid = order.getUid();
        accountService.activeOrder(order);
        Account account = accountService.getAccount(uid);
        xrayService.addClient(uid, account.getAid());
        xrayService.syncConfig();
        return "redirect:/orders";
    }

    @DeleteMapping(value = "order/{oid}")
    public String deleteOrder(@PathVariable("oid") String oid) {
        orderService.deleteOrder(oid);
        return "redirect:/orders";
    }

    @PostMapping(value = "/pay")
    public String toPayPage(Model model, @RequestParam("days") Integer days) {
        model.addAttribute("days", days);
        return "pay";
    }

    @PostMapping(value = "/order")
    public String addOrder(@RequestParam("uid") Integer uid,
                           @RequestParam("days") Integer days) {
        orderService.addOrder(uid, days);
        return "redirect:/user/orders";
    }

}
