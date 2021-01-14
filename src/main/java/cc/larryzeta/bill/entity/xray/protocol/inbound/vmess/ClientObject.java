package cc.larryzeta.bill.entity.xray.protocol.inbound.vmess;

import cc.larryzeta.bill.entity.Client;
import lombok.Data;

@Data
public class ClientObject extends Client {

    private Integer level;
    private Integer alterId;

}
