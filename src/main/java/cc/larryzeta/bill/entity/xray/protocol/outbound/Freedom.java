package cc.larryzeta.bill.entity.xray.protocol.outbound;

import lombok.Data;

@Data
public class Freedom extends OutboundConfigurationObject{

    private String domainStrategy;
    private String redirect;
    private Integer userLevel;

}
