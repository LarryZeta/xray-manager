package cc.larryzeta.manager.biz;

import cc.larryzeta.manager.dao.AccountDAO;
import cc.larryzeta.manager.dao.XrayDAO;
import cc.larryzeta.manager.entity.Account;
import cc.larryzeta.manager.entity.XrayConfig;
import cc.larryzeta.manager.entity.xray.InboundObject;
import cc.larryzeta.manager.entity.xray.protocol.inbound.vless.VLESS;
import cc.larryzeta.manager.entity.xray.protocol.inbound.vmess.VMESS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class XrayBiz {

//    @Autowired
//    private XrayDAO xrayDAO;

    @Autowired
    private AccountDAO accountDAO;

//    public Boolean findClient(String email) {
//
//        XrayConfig xrayConfig = xrayDAO.getXrayConfig();
//        for (InboundObject inbound : xrayConfig.getInbounds()) {
//            if (VLESS.class.equals(inbound.getClass())) {
//                VLESS vless = (VLESS) inbound;
//                return vless.getSettings().getClients().stream().anyMatch(clientObject -> email.equals(clientObject.getEmail()));
//            } else if (VMESS.class.equals(inbound.getClass())) {
//                VMESS vmess = (VMESS) inbound;
//                return vmess.getSettings().getClients().stream().anyMatch(clientObject -> email.equals(clientObject.getEmail()));
//            } else {
//                return false;
//            }
//        }
//
//        return false;
//    }
//
//   public String genConfig() {
//
//        List<Account> accounts = accountDAO.getAllAccount();
//        return null;
//   }
//
//    public Boolean addClient(String email, String uuid) {
//
//        XrayConfig xrayConfig = xrayDAO.getXrayConfig();
//        for (InboundObject inbound : xrayConfig.getInbounds()) {
//            if (VLESS.class.equals(inbound.getClass())) {
//                VLESS vless = (VLESS) inbound;
//                cc.larryzeta.manager.entity.xray.protocol.inbound.vless.ClientObject vlessClient
//                        = new cc.larryzeta.manager.entity.xray.protocol.inbound.vless.ClientObject();
//                vlessClient.setEmail(email);
//                vlessClient.setId(uuid);
//                vlessClient.setFlow("xtls-rprx-direct");
//                vlessClient.setLevel(1);
//                vless.getSettings().getClients().add(vlessClient);
//            } else if (VMESS.class.equals(inbound.getClass())) {
//                VMESS vmess = (VMESS) inbound;
//                cc.larryzeta.manager.entity.xray.protocol.inbound.vmess.ClientObject vmessClient
//                        = new cc.larryzeta.manager.entity.xray.protocol.inbound.vmess.ClientObject();
//                vmessClient.setEmail(email);
//                vmessClient.setId(uuid);
//                vmessClient.setAlterId(16);
//                vmessClient.setLevel(1);
//                vmess.getSettings().getClients().add(vmessClient);
//            } else {
//                return false;
//            }
//        }
//        xrayDAO.saveXrayConfig(xrayConfig);
//
//        return true;
//
//    }
//
//    public Boolean deleteClient(String email) {
//
//        XrayConfig xrayConfig = xrayDAO.getXrayConfig();
//        for (InboundObject inbound : xrayConfig.getInbounds()) {
//            if (VLESS.class.equals(inbound.getClass())) {
//                VLESS vless = (VLESS) inbound;
//                vless.getSettings().getClients().removeIf(clientObject -> email.equals(clientObject.getEmail()));
//            } else if (VMESS.class.equals(inbound.getClass())) {
//                VMESS vmess = (VMESS) inbound;
//                vmess.getSettings().getClients().removeIf(clientObject -> email.equals(clientObject.getEmail()));
//            } else {
//                return false;
//            }
//        }
//        xrayDAO.saveXrayConfig(xrayConfig);
//
//        return true;
//
//    }

}
