package cc.larryzeta.bill.entity.xray.protocol.inbound.vless;

import cc.larryzeta.bill.entity.xray.InboundObject;
import lombok.Data;

@Data
public class VLESS extends InboundObject {
    private VlessConfig settings;
}

