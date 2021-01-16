package cc.larryzeta.manager.entity.xray.protocol.inbound.vless;

import cc.larryzeta.manager.entity.Client;
import lombok.Data;

@Data
public class ClientObject extends Client {

    private Integer level;
    private String flow;

}
