package cc.larryzeta.manager.entity.xray;

import lombok.Data;

import java.util.Map;

@Data
public class PolicyObject {

    private Map<String, LevelPolicyObject> level;
    private SystemPolicyObject system;

}

@Data
class LevelPolicyObject {

    private Integer handshake;
    private Integer connIdle;
    private Integer uplinkOnly;
    private Integer downlinkOnly;
    private Boolean statsUserUplink;
    private Boolean statsUserDownlink;
    private Integer bufferSize;

}

@Data
class SystemPolicyObject {

    private Boolean statsInboundUplink;
    private Boolean statsInboundDownlink;
    private Boolean statsOutboundUplink;
    private Boolean statsOutboundDownlink;

}