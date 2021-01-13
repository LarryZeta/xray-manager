package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.biz.XrayBiz;
import cc.larryzeta.bill.dao.UserDAO;
import cc.larryzeta.bill.dao.XrayDAO;
import cc.larryzeta.bill.entity.Client;
import cc.larryzeta.bill.service.XrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class XrayServiceImpl implements XrayService {

    private final static String RESTART = "service xray restart";

    @Resource
    private UserDAO userDAO;

    @Autowired
    private XrayBiz xrayBiz;

    @Override
    public List<Client> getAllClients() {
        return xrayBiz.getAllClients();
    }

    @Override
    public Boolean addClient(Integer uid, String uuid) {
        String email = userDAO.getUserByUid(uid).getEmail();
        if (xrayBiz.findClient(email)) {
            return false;
        } else {
            try {
                xrayBiz.addClient(email, uuid);
                Process process = Runtime.getRuntime().exec(RESTART);
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
            if(xrayBiz.deleteClient(email)) {
                Process process = Runtime.getRuntime().exec(RESTART);
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
