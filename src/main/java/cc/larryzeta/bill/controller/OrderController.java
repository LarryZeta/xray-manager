package cc.larryzeta.bill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @PostMapping(value = "/order/{uid}")
    public String addOrder(@PathVariable("uid")Integer uid) {
//        TODO 添加订单，订单分类还没实现
        return "redirect:/clients";
    }

}
