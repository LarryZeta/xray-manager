package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.entities.Account;
import cc.larryzeta.bill.entities.Order;
import cc.larryzeta.bill.service.AccountService;
import cc.larryzeta.bill.service.OrderService;
import cc.larryzeta.bill.service.V2rayService;
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
    private V2rayService v2rayService;

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
        v2rayService.addClient(uid, account.getAid());
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
