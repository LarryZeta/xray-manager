package cc.larryzeta.manager.entity.xray;

import lombok.Data;

import java.util.Map;

@Data
public class TransportObject {

    private TcpObject tcpSettings;
    private KcpObject kcpSettings;
    private WebSocketObject wsSettings;
    private HttpObject httpSettings;
    private QuicObject quicSettings;
    private DomainSocketObject dsSettings;

}

class TcpObject {

}

class KcpObject {

}

@Data
class WebSocketObject {

    private Boolean acceptProxyProtocol;
    private String path;
    private Map<String, String> headers;

}

class HttpObject {

}

class QuicObject {

}

class DomainSocketObject {

}