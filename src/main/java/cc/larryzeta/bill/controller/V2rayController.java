package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class V2rayController {

    @Autowired
    V2rayService v2rayService;

    @GetMapping(value = "/orders")
    public String getClients(Model model) {
        v2rayService.getAllClients(model);
        return "orders";
    }

}
