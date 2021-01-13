package cc.larryzeta.bill.controller;

import cc.larryzeta.bill.service.XrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class XrayController {

    @Autowired
    private XrayService xrayService;

    @GetMapping(value = "/clients")
    public String getClients(Model model) {
        model.addAttribute("clients", xrayService.getAllClients());
        return "clients";
    }

    @GetMapping(value = "/addClient")
    public String addClient() {
        xrayService.addClient(0, UUID.randomUUID().toString());
        return "redirect:/clients";
    }

    @DeleteMapping(value = "/client/{email}")
    public String deleteClient(@PathVariable("email")String email) {
        xrayService.deleteClient(email);
        return "redirect:/clients";
    }

}
