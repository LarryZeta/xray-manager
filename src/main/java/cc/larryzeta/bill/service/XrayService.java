package cc.larryzeta.bill.service;

public interface XrayService {

    Boolean addClient(Integer uid, String uuid);

    Boolean deleteClient(String email);

    Boolean deleteClient(Integer uid);

}
