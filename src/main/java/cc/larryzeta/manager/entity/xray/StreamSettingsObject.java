package cc.larryzeta.manager.entity.xray;

import lombok.Data;

import java.util.List;

@Data
public class StreamSettingsObject extends TransportObject{

    private String network;
    private String security;
    private TLSObject tlsSettings;
    private XTLSObject xtlsSettings;
    private SockoptObject sockopt;

}

@Data
class TLSObject {
    private String serverName;
    private List<String> alpn;
    private String minVersion;
    private String maxVersion;
    private Boolean preferServerCipherSuites;
    private String cipherSuites;
    private Boolean allowInsecure;
    private Boolean disableSystemRoot;
    private Boolean enableSessionResumption;
    private List<CertificateObject> certificates;
}

@Data
class XTLSObject extends TLSObject{
}

@Data
class CertificateObject {

    private Integer ocspStapling;
    private String usage;
    private String certificateFile;
    private List<String> certificate;
    private String keyFile;
    private List<String> key;

}

@Data
class SockoptObject {

    private Integer mark;
    private Boolean tcpFastOpen;
    private String tproxy;

}