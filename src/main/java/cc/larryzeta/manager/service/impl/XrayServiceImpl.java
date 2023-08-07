package cc.larryzeta.manager.service.impl;


import cc.larryzeta.manager.biz.XrayBiz;
import cc.larryzeta.manager.config.FeignConfig;
import cc.larryzeta.manager.dao.UserBaseInfoDAO;
import cc.larryzeta.manager.dao.XrayAccountInfoDAO;
import cc.larryzeta.manager.dao.XrayServerInfoDAO;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.entity.TXrayAccountInfo;
import cc.larryzeta.manager.entity.TXrayServerInfo;
import cc.larryzeta.manager.enumeration.StatusEnum;
import cc.larryzeta.manager.external.FlaskApi;
import cc.larryzeta.manager.external.model.Client;
import cc.larryzeta.manager.service.XrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class XrayServiceImpl implements XrayService {

    @Autowired
    private XrayAccountInfoDAO accountInfoDAO;

    @Autowired
    private UserBaseInfoDAO userBaseInfoDAO;

    @Autowired
    private XrayServerInfoDAO xrayServerInfoDao;

    @Autowired
    private XrayBiz xrayBiz;

    @Override
    public void syncClient() {

        TXrayAccountInfo query = new TXrayAccountInfo();
        query.setAccountStatus(StatusEnum.VALID.code);
        List<TXrayAccountInfo> xrayAccountInfoList = accountInfoDAO.getTXrayAccountInfo(query);

        List<Client> clientList = new ArrayList<>(xrayAccountInfoList.size());

        for (TXrayAccountInfo xrayAccountInfo : xrayAccountInfoList) {
            TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfoById(xrayAccountInfo.getUserId());
            if (userBaseInfo == null || StatusEnum.INVALID.code.equals(userBaseInfo.getUserStatus())) {
                continue;
            }
            Client client = new Client();
            client.setId(xrayAccountInfo.getUuid());
            client.setEmail(userBaseInfo.getEmail());
            clientList.add(client);
        }

        List<TXrayServerInfo> xrayServerInfoList = xrayServerInfoDao.getAllXrayServerInfo();;

        for (TXrayServerInfo xrayServerInfo : xrayServerInfoList) {
            xrayBiz.syncClient(clientList, xrayServerInfo);
        }

    }
}
