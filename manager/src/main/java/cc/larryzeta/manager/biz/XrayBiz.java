package cc.larryzeta.manager.biz;

import cc.larryzeta.manager.config.FeignConfig;
import cc.larryzeta.manager.dao.XrayServerInfoDAO;
import cc.larryzeta.manager.entity.TXrayServerInfo;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.external.model.AddRequest;
import cc.larryzeta.manager.external.FlaskApi;
import cc.larryzeta.manager.external.model.Client;
import cc.larryzeta.manager.external.model.RemoveRequest;
import cc.larryzeta.manager.external.model.Response;
import cc.larryzeta.manager.external.model.SyncRequest;
import cc.larryzeta.manager.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class XrayBiz {


    public void syncClient(List<Client> clientList, TXrayServerInfo xrayServerInfo) {

        log.info("[syncClient] clientList=[{}], xrayServerInfo=[{}]", JsonUtils.toJSONString(clientList), JsonUtils.toJSONString(xrayServerInfo));

        FlaskApi flaskApi = FeignConfig.getFlaskApi(FlaskApi.class, xrayServerInfo.getAddress());

        SyncRequest syncRequest = new SyncRequest();
        syncRequest.setClients(clientList);

        if (flaskApi == null) {
            throw new BizException("服务器不存在");
        }

        flaskApi.syncClients(syncRequest, xrayServerInfo.getToken());

    }

}
