package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.api.model.ResultEntity;
import cc.larryzeta.manager.api.order.OrderControllerApi;
import cc.larryzeta.manager.api.order.model.OrderDTO;
import cc.larryzeta.manager.entity.Account;
import cc.larryzeta.manager.entity.Order;
import cc.larryzeta.manager.service.AccountService;
import cc.larryzeta.manager.service.OrderService;
import cc.larryzeta.manager.service.XrayService;
import feign.RequestLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class OrderController implements OrderControllerApi {

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

    @PostMapping("/order/te")
    @ResponseBody
    @Override
    public ResultEntity<String> AddOrder(@RequestBody OrderDTO orderDTO) {
//        orderService.addOrder(orderDTO.getUserId(), orderDTO.getDays());
        return null;
    }

    @DeleteMapping("/order/{orderId}")
    @ResponseBody
    @Override
    public ResultEntity<String> deleteOrder(@PathVariable String orderId) {

        ResultEntity<String> resultEntity = new ResultEntity<>();
        resultEntity.setData(String.valueOf(orderService.deleteOrder(orderId)));

        return resultEntity;
    }

    @GetMapping("/order/{orderId}")
    @ResponseBody
    @Override
    public ResultEntity<List<OrderDTO>> queryOrder(@PathVariable String orderId) {
        ResultEntity<List<OrderDTO>> resultEntity = new ResultEntity<>();
//        orderService.getAllOrders();

        return resultEntity;
    }

    @PutMapping("/order/{orderId}/active")
    @ResponseBody
    @Override
    public ResultEntity<OrderDTO> activeOrder(@PathVariable String orderId) {

        return null;
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
