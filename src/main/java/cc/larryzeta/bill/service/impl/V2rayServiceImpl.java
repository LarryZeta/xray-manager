package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.dao.V2rayDAO;
import cc.larryzeta.bill.entities.Client;
import cc.larryzeta.bill.service.V2rayService;
import org.springframework.stereotype.Service;

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
            return v2rayDAO.addClient(email);
        }
    }

    @Override
    public Boolean deleteClient(String email) {
        return v2rayDAO.deleteClient(email);
    }


}
