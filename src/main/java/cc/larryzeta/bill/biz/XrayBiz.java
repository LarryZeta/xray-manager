package cc.larryzeta.bill.biz;

import cc.larryzeta.bill.dao.XrayDAO;
import cc.larryzeta.bill.entity.Client;
import cc.larryzeta.bill.entity.XrayConfig;
import cc.larryzeta.bill.entity.xray.InboundObject;
import cc.larryzeta.bill.entity.xray.protocol.inbound.vless.VLESS;
import cc.larryzeta.bill.entity.xray.protocol.inbound.vmess.VMESS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class XrayBiz {

    @Autowired
    private XrayDAO xrayDAO;

    public Boolean findClient(String email) {

        XrayConfig xrayConfig = xrayDAO.getXrayConfig();
        for (InboundObject inbound : xrayConfig.getInbounds()) {
            if (VLESS.class.equals(inbound.getClass())) {
                VLESS vless = (VLESS) inbound;
                return vless.getSettings().getClients().stream().anyMatch(clientObject -> email.equals(clientObject.getEmail()));
            } else if (VMESS.class.equals(inbound.getClass())) {
                VMESS vmess = (VMESS) inbound;
                return vmess.getSettings().getClients().stream().anyMatch(clientObject -> email.equals(clientObject.getEmail()));
            } else {
                return false;
            }
        }

        return false;
    }

    public Boolean addClient(String email, String uuid) {

        XrayConfig xrayConfig = xrayDAO.getXrayConfig();
        for (InboundObject inbound : xrayConfig.getInbounds()) {
            if (VLESS.class.equals(inbound.getClass())) {
                VLESS vless = (VLESS) inbound;
                cc.larryzeta.bill.entity.xray.protocol.inbound.vless.ClientObject vlessClient
                        = new cc.larryzeta.bill.entity.xray.protocol.inbound.vless.ClientObject();
                vlessClient.setEmail(email);
                vlessClient.setId(uuid);
                vlessClient.setFlow("xtls-rprx-direct");
                vlessClient.setLevel(1);
                vless.getSettings().getClients().add(vlessClient);
            } else if (VMESS.class.equals(inbound.getClass())) {
                VMESS vmess = (VMESS) inbound;
                cc.larryzeta.bill.entity.xray.protocol.inbound.vmess.ClientObject vmessClient
                        = new cc.larryzeta.bill.entity.xray.protocol.inbound.vmess.ClientObject();
                vmessClient.setEmail(email);
                vmessClient.setId(uuid);
                vmessClient.setAlterId(16);
                vmessClient.setLevel(1);
                vmess.getSettings().getClients().add(vmessClient);
            } else {
                return false;
            }
        }
        xrayDAO.saveXrayConfig(xrayConfig);

        return true;

    }

    public Boolean deleteClient(String email) {

        XrayConfig xrayConfig = xrayDAO.getXrayConfig();
        for (InboundObject inbound : xrayConfig.getInbounds()) {
            if (VLESS.class.equals(inbound.getClass())) {
                VLESS vless = (VLESS) inbound;
                vless.getSettings().getClients().removeIf(clientObject -> email.equals(clientObject.getEmail()));
            } else if (VMESS.class.equals(inbound.getClass())) {
                VMESS vmess = (VMESS) inbound;
                vmess.getSettings().getClients().removeIf(clientObject -> email.equals(clientObject.getEmail()));
            } else {
                return false;
            }
        }
        xrayDAO.saveXrayConfig(xrayConfig);

        return true;

    }

}
