package cc.larryzeta.bill.entity.xray;

import lombok.Data;

import java.util.Map;

@Data
public class PolicyObject {

    private Map<String, LevelPolicyObject> level;
    private SystemPolicyObject system;

}

@Data
class LevelPolicyObject {

    private int handshake;
    private int connIdle;
    private int uplinkOnly;
    private int downlinkOnly;
    private boolean statsUserUplink;
    private boolean statsUserDownlink;
    private int bufferSize;

}

@Data
class SystemPolicyObject {

    private boolean statsInboundUplink;
    private boolean statsInboundDownlink;
    private boolean statsOutboundUplink;
    private boolean statsOutboundDownlink;

}