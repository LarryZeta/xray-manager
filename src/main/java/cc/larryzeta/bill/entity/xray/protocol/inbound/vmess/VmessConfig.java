package cc.larryzeta.bill.entity.xray.protocol.inbound.vmess;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class VmessConfig {

    private List<ClientObject> clients;
    private DetourObject detour;
    @JsonProperty("default")
    private DefaultObject _default;

}
