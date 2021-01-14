package cc.larryzeta.bill.entity.xray;

import lombok.Data;

@Data
public class OutboundObject {

    private String sendThrough;
    private String protocol;
    private Object settings;
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

    private Boolean enabled;
    private Integer concurrency;

}