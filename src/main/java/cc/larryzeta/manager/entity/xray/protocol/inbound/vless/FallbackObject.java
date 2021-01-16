package cc.larryzeta.manager.entity.xray.protocol.inbound.vless;

import lombok.Data;

@Data
public class FallbackObject {

    private String alpn;
    private String path;
    private Object dest;
    private Integer xver;

}
