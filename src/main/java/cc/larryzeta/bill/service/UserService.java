package cc.larryzeta.bill.service;

import cc.larryzeta.bill.entities.User;

public interface UserService {

    User getUserByEmail(String email);

    Integer registerUser(User user);

}
