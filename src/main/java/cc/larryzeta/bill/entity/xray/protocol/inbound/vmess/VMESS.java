package cc.larryzeta.bill.entity.xray.protocol.inbound.vmess;

import cc.larryzeta.bill.entity.xray.InboundObject;
import lombok.Data;

@Data
public class VMESS extends InboundObject {
    private VmessConfig settings;
}

