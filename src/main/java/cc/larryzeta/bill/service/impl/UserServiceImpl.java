package cc.larryzeta.bill.service.impl;

import cc.larryzeta.bill.dao.UserDAO;
import cc.larryzeta.bill.entities.User;
import cc.larryzeta.bill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public Integer registerUser(User user) {
        return userDAO.registerUser(user);
    }

}
