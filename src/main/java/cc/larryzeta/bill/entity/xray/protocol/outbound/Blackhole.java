package cc.larryzeta.bill.entity.xray.protocol.outbound;

import lombok.Data;

@Data
public class Blackhole extends OutboundConfigurationObject{

    private ResponseObject response;

}

@Data
class ResponseObject {
    private String type;
}