package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.dao.V2rayDAO;
import cc.larryzeta.bill.entities.Client;
import cc.larryzeta.bill.service.V2rayService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class V2rayServiceImpl implements V2rayService {

    private V2rayDAO v2rayDAO = new V2rayDAO();

    @Override
    public List<Client> getAllClients() {
        return v2rayDAO.getAllClients();
    }

    @Override
    public Boolean addClient(String email) {
        if (v2rayDAO.findClient(email)) {
            return false;
        } else {
            try {
                v2rayDAO.addClient(email);
                Process process = Runtime.getRuntime().exec("service v2ray restart");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public Boolean deleteClient(String email) {

        try {
            if(v2rayDAO.deleteClient(email)) {
                Process process = Runtime.getRuntime().exec("service v2ray restart");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


}
