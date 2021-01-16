package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.biz.XrayBiz;
import cc.larryzeta.manager.dao.UserDAO;
import cc.larryzeta.manager.service.XrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class XrayServiceImpl implements XrayService {

    private final static String RESTART = "service xray restart";

    @Resource
    private UserDAO userDAO;

    @Autowired
    private XrayBiz xrayBiz;

    @Override
    public Boolean addClient(Integer uid, String uuid) {
        String email = userDAO.getUserByUid(uid).getEmail();
        if (xrayBiz.findClient(email)) {
            return false;
        } else {
            try {
                xrayBiz.addClient(email, uuid);
                Runtime.getRuntime().exec(RESTART);
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
                Runtime.getRuntime().exec(RESTART);
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
