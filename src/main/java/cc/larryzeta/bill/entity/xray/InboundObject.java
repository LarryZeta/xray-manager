package cc.larryzeta.bill.entity.xray;

import cc.larryzeta.bill.entity.xray.protocol.inbound.InboundConfigurationObject;
import lombok.Data;

import java.util.List;

@Data
public class InboundObject {

    private String address;
    private int port;
    private String protocol;
    private List<InboundConfigurationObject> settings;
    private StreamSettingsObject streamSettings;
    private String tag;
    private SniffingObject sniffing;
    private AllocateObject allocate;

}

@Data
class SniffingObject {

    private boolean enabled;
    private String destOverride;

}

@Data
class AllocateObject {

    private String strategy;
    private int refresh;
    private int concurrency;

}