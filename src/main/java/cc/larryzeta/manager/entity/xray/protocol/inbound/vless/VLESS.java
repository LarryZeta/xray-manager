package cc.larryzeta.manager.entity.xray.protocol.inbound.vless;

import cc.larryzeta.manager.entity.xray.InboundObject;
import lombok.Data;

@Data
public class VLESS extends InboundObject {
    private VlessConfig settings;
}

