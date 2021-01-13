package cc.larryzeta.bill.entity.xray;

import lombok.Data;

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

class WebSocketObject {

}

class HttpObject {

}

class QuicObject {

}

class DomainSocketObject {

}