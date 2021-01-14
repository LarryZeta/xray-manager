package cc.larryzeta.bill.entity.xray.protocol.inbound.vless;

import cc.larryzeta.bill.entity.Client;
import lombok.Data;

@Data
public class ClientObject extends Client {

    private Integer level;
    private String flow;

}
