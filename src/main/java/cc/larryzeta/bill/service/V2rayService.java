package cc.larryzeta.bill.service;

import cc.larryzeta.bill.entities.Client;

import java.util.List;

public interface V2rayService {

    List<Client> getAllClients();

    Boolean addClient(Integer uid, String uuid);

    Boolean deleteClient(String email);

}
