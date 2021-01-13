package cc.larryzeta.bill.service;

import cc.larryzeta.bill.entity.Client;

import java.util.List;

public interface XrayService {

    List<Client> getAllClients();

    Boolean addClient(Integer uid, String uuid);

    Boolean deleteClient(String email);

    Boolean deleteClient(Integer uid);

}
