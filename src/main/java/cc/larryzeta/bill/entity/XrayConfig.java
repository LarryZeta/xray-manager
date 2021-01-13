package cc.larryzeta.bill.entity;

import cc.larryzeta.bill.entity.xray.*;
import cc.larryzeta.bill.entity.xray.InboundObject;
import cc.larryzeta.bill.entity.xray.OutboundObject;
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
