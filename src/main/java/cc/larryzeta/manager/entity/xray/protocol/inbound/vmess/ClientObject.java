package cc.larryzeta.manager.entity.xray.protocol.inbound.vmess;

import cc.larryzeta.manager.entity.Client;
import lombok.Data;

@Data
public class ClientObject extends Client {

    private Integer level;
    private Integer alterId;

}
