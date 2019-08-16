package cc.larryzeta.bill.entities;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class V2rayConfig {

    private Map<String, String> log;
    private List<Object> inbounds;
    private List<Object> outbounds;
    private Map<String, Object> dns;
    private Map<String, Object> routing;
    private Map<String, Object> transport;

    public Map<String, String> getLog() {
        return log;
    }

    public void setLog(Map<String, String> log) {
        this.log = log;
    }

    public List<Object> getInbounds() {
        return inbounds;
    }

    public void setInbounds(List<Object> inbounds) {
        this.inbounds = inbounds;
    }

    public List<Object> getOutbounds() {
        return outbounds;
    }

    public void setOutbounds(List<Object> outbounds) {
        this.outbounds = outbounds;
    }

    public Map<String, Object> getDns() {
        return dns;
    }

    public void setDns(Map<String, Object> dns) {
        this.dns = dns;
    }

    public Map<String, Object> getRouting() {
        return routing;
    }

    public void setRouting(Map<String, Object> routing) {
        this.routing = routing;
    }

    public Map<String, Object> getTransport() {
        return transport;
    }

    public void setTransport(Map<String, Object> transport) {
        this.transport = transport;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}
