package cc.larryzeta.bill.entity;


import cc.larryzeta.bill.entity.xray.ApiObject;
import cc.larryzeta.bill.entity.xray.DnsObject;
import cc.larryzeta.bill.entity.xray.InboundObject;
import cc.larryzeta.bill.entity.xray.LogObject;
import cc.larryzeta.bill.entity.xray.OutboundObject;
import cc.larryzeta.bill.entity.xray.PolicyObject;
import cc.larryzeta.bill.entity.xray.ReverseObject;
import cc.larryzeta.bill.entity.xray.RoutingObject;
import cc.larryzeta.bill.entity.xray.TransportObject;
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
