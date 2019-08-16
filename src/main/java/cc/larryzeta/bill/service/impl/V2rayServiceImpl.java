package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.dao.V2rayDAO;
import cc.larryzeta.bill.service.V2rayService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.FileNotFoundException;

@Service
public class V2rayServiceImpl implements V2rayService {

    private V2rayDAO v2rayDAO = new V2rayDAO();

    @Override
    public Boolean getAllClients(Model model) {
        try {
            model.addAttribute("clients", v2rayDAO.getAllClients());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
