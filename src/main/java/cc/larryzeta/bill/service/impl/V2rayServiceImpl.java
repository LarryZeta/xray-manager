package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.dao.UserDAO;
import cc.larryzeta.bill.dao.V2rayDAO;
import cc.larryzeta.bill.entities.Client;
import cc.larryzeta.bill.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class V2rayServiceImpl implements V2rayService {

    @Autowired
    private UserDAO userDAO;
    private V2rayDAO v2rayDAO = new V2rayDAO();

    @Override
    public List<Client> getAllClients() {
        return v2rayDAO.getAllClients();
    }

    @Override
    public Boolean addClient(Integer uid, String uuid) {
        String email = userDAO.getUserByUid(uid).getEmail();
        if (v2rayDAO.findClient(email)) {
            return false;
        } else {
            try {
                v2rayDAO.addClient(email, uuid);
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

    @Override
    public Boolean deleteClient(Integer uid) {
        String email = userDAO.getUserByUid(uid).getEmail();
        return deleteClient(email);
    }


}
