package cc.larryzeta.manager.entity.xray.protocol.inbound.vmess;

import cc.larryzeta.manager.entity.xray.InboundObject;
import lombok.Data;

@Data
public class VMESS extends InboundObject {
    private VmessConfig settings;
}

