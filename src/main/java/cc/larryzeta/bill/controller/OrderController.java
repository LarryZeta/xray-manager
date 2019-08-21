package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.entities.Order;
import cc.larryzeta.bill.service.AccountService;
import cc.larryzeta.bill.service.OrderService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/orders")
    public String getClients(Model model) {
        model.addAttribute("orders", orderService.getNotActiveOrders());
        return "orders";
    }

    @PostMapping(value = "/order")
    public String activeOrder(@RequestParam("uid")String uid, @RequestParam("days")String days) {
        Order order = new Order();
        order.setUid(Integer.parseInt(uid));
        order.setDays(Integer.parseInt(days));
        accountService.activeOrder(order);
        return "redirect:/orders";
    }

    @PostMapping(value = "/order/{uid}")
    public String addOrder(@PathVariable("uid")Integer uid) {
//        TODO 添加订单，订单分类还没实现
        orderService.addOrder(uid, 30);
        return "redirect:/clients";
    }

}
