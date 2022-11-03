package cc.larryzeta.manager.service;

public interface XrayService {

    @Deprecated
    Boolean addClient(Integer uid, String uuid);

    @Deprecated
    Boolean deleteClient(String email);

    @Deprecated
    Boolean deleteClient(Integer uid);

    /**
     * 1. generate config.json from Accounts
     * 2. scp config.json to all node
     * 3. send xray restart command
     */
    void syncConfig();

}
