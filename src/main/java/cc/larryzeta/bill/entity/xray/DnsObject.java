package cc.larryzeta.bill.entity.xray;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DnsObject {

    /**
     * string, ip address
     */
    private Map<String, String> hosts;

    private List<Object> servers;

    private String clientIp;

    private String tag;

}

@Data
class ServerObject {

    private String address;
    private Integer port;
    private List<String> domains;
    private List<String> expectIPs;

}
