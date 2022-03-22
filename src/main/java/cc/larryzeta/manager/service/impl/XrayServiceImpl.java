package cc.larryzeta.manager.service.impl;

import cc.larryzeta.manager.biz.XrayBiz;
import cc.larryzeta.manager.mapper.UserDAO;
import cc.larryzeta.manager.service.XrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class XrayServiceImpl implements XrayService {

    @Resource
    private UserDAO userDAO;

    @Autowired
    private XrayBiz xrayBiz;

    @Override
    public Boolean addClient(Integer uid, String uuid) {
        String email = userDAO.getUserByUid(uid).getEmail();
        return xrayBiz.addClient(email, uuid);
    }

    @Override
    public Boolean deleteClient(String email) {
        return xrayBiz.deleteClient(email);
    }

    @Override
    public Boolean deleteClient(Integer uid) {
        String email = userDAO.getUserByUid(uid).getEmail();
        return deleteClient(email);
    }


}
