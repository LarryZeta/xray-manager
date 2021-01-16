package cc.larryzeta.manager.entity;


import cc.larryzeta.manager.entity.xray.ApiObject;
import cc.larryzeta.manager.entity.xray.DnsObject;
import cc.larryzeta.manager.entity.xray.InboundObject;
import cc.larryzeta.manager.entity.xray.LogObject;
import cc.larryzeta.manager.entity.xray.OutboundObject;
import cc.larryzeta.manager.entity.xray.PolicyObject;
import cc.larryzeta.manager.entity.xray.ReverseObject;
import cc.larryzeta.manager.entity.xray.RoutingObject;
import cc.larryzeta.manager.entity.xray.TransportObject;
import lombok.Data;

import java.util.List;

@Data
public class XrayConfig {

    private LogObject log;
    private ApiObject api;
    private DnsObject dns;
    private PolicyObject policy;
    private List<InboundObject> inbounds;
    private List<OutboundObject> outbounds;
    private TransportObject transport;
    private RoutingObject routing;
    private ReverseObject reverse;

}
