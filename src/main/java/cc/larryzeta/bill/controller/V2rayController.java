package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class V2rayController {

    @Autowired
    V2rayService v2rayService;

    @GetMapping(value = "/orders")
    public String getClients(Model model) {
        model.addAttribute("clients", v2rayService.getAllClients());
        return "orders";
    }

    @GetMapping(value = "/addClient")
    public String addClient() {
        v2rayService.addClient();
        return "redirect:/orders";
    }

    @GetMapping(value = "/deleteClient")
    public String deleteClient() {
        v2rayService.deleteClient();
        return "redirect:/orders";
    }

}
