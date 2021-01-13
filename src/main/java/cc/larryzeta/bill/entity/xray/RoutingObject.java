package cc.larryzeta.bill.entity.xray;

import lombok.Data;

import java.util.List;

@Data
public class RoutingObject {

    private String domainStrategy;
    private List<RuleObject> rules;
    private List<BalancerObject> balancerObjects;

}

@Data
class RuleObject {

    private String type;
    private List<String> domain;
    private List<String> ip;
    private String port;
    private String sourcePort;
    private String network;
    private List<String> source;
    private List<String> user;
    private List<String> inboundTag;
    private String protocol;
    private String attrs;
    private String outboundTag;
    private String balancerTag;

}

@Data
class BalancerObject {

    private String tag;
    private List<String> selector;

}