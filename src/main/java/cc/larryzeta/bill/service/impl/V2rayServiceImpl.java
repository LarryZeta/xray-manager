package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.dao.UserDAO;
import cc.larryzeta.bill.dao.XrayDAO;
import cc.larryzeta.bill.entities.Client;
import cc.larryzeta.bill.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class V2rayServiceImpl implements V2rayService {

    @Resource
    private UserDAO userDAO;

    @Autowired
    private XrayDAO xrayDAO;

    @Override
    public List<Client> getAllClients() {
        return xrayDAO.getAllClients();
    }

    @Override
    public Boolean addClient(Integer uid, String uuid) {
        String email = userDAO.getUserByUid(uid).getEmail();
        if (xrayDAO.findClient(email)) {
            return false;
        } else {
            try {
                xrayDAO.addClient(email, uuid);
                Process process = Runtime.getRuntime().exec("service xray restart");
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
            if(xrayDAO.deleteClient(email)) {
                Process process = Runtime.getRuntime().exec("service xray restart");
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
