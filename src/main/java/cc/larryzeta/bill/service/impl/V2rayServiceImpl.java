package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.dao.V2rayDAO;
import cc.larryzeta.bill.entities.Client;
import cc.larryzeta.bill.entities.V2rayConfig;
import cc.larryzeta.bill.service.V2rayService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Service
public class V2rayServiceImpl implements V2rayService {

    private V2rayDAO v2rayDAO = new V2rayDAO();

    @Override
    public Boolean getAllClients(Model model) {
        try {
            V2rayConfig v2rayConfig = v2rayDAO.getV2rayConfig();
            Map<String, List<Client>> clientsMap = (Map<String, List<Client>>) v2rayConfig.getInbounds().get(0).get("settings");
            List<Client> clients = clientsMap.get("clients");
            model.addAttribute("clients", clients);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
