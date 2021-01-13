package cc.larryzeta.bill.entity.xray;

import lombok.Data;

@Data
public class StreamSettingsObject extends TransportObject{

    private String network;
    private String security;
    private TLSObject tlsSettings;
    private SockoptObject sockopt;

}

@Data
class TLSObject {

}

@Data
class SockoptObject {

    private int mark;
    private boolean tcpFastOpen;
    private String tproxy;

}