package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class V2rayController {

    @Autowired
    private V2rayService v2rayService;

    @GetMapping(value = "/clients")
    public String getClients(Model model) {
        model.addAttribute("clients", v2rayService.getAllClients());
        return "clients";
    }

    @GetMapping(value = "/addClient")
    public String addClient() {
        v2rayService.addClient(0, UUID.randomUUID().toString());
        return "redirect:/clients";
    }

    @DeleteMapping(value = "/client/{email}")
    public String deleteClient(@PathVariable("email")String email) {
        v2rayService.deleteClient(email);
        return "redirect:/clients";
    }

}
