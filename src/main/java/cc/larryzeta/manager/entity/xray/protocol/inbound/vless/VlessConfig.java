package cc.larryzeta.manager.entity.xray.protocol.inbound.vless;

import lombok.Data;

import java.util.List;

@Data
public class VlessConfig {

    private List<ClientObject> clients;
    private String decryption;
    private List<FallbackObject> fallbacks;

}
