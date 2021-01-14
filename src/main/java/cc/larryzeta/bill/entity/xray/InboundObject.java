package cc.larryzeta.bill.entity.xray;

import cc.larryzeta.bill.entity.xray.protocol.inbound.vless.VLESS;
import cc.larryzeta.bill.entity.xray.protocol.inbound.vmess.VMESS;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "protocol")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = VLESS.class, name = "vless"),
        @JsonSubTypes.Type(value = VMESS.class, name = "vmess"),
})
public abstract class InboundObject {

    private String listen;
    private Integer port;
    private String protocol;
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