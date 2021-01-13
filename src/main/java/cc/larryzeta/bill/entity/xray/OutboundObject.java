package cc.larryzeta.bill.entity.xray;

import cc.larryzeta.bill.entity.xray.protocol.outbound.OutboundConfigurationObject;
import lombok.Data;

@Data
public class OutboundObject {

    private String sendThrough;
    private String protocol;
    private OutboundConfigurationObject settings;
    private String tag;
    private StreamSettingsObject streamSettings;
    private ProxySettingsObject proxySettings;
    private MuxObject mux;

}

@Data
class ProxySettingsObject {
    private String tag;
}

@Data
class MuxObject {

    private boolean enabled;
    private int concurrency;

}