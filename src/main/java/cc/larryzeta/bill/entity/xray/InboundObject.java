package cc.larryzeta.bill.entity.xray;

import lombok.Data;

@Data
public class InboundObject {

    private String listen;
    private Integer port;
    private String protocol;
    private Object settings;
    private StreamSettingsObject streamSettings;
    private String tag;
    private SniffingObject sniffing;
    private AllocateObject allocate;

}

@Data
class SniffingObject {

    private Boolean enabled;
    private String destOverride;

}

@Data
class AllocateObject {

    private String strategy;
    private Integer refresh;
    private Integer concurrency;

}