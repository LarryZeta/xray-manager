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

    @Autowired
    private XrayServerInfoDAO xrayServerInfoDao;

    private Map<String, FlaskApi> loadFlaskApiMap() {
        List<TXrayServerInfo> xrayServerInfoList = xrayServerInfoDao.getAllXrayServerInfo();
        Map<String, FlaskApi> flaskApiMap = new HashMap<>(xrayServerInfoList.size());
        for (TXrayServerInfo xrayServerInfo : xrayServerInfoList) {
            flaskApiMap.put(xrayServerInfo.getServerName(), FeignConfig.getFlaskApi(FlaskApi.class, xrayServerInfo.getAddress()));
        }
        return flaskApiMap;
    }


    public Boolean addClient(String email, String uuid) {

        AddRequest addRequest = new AddRequest();
        addRequest.setEmail(email);
        addRequest.setId(uuid);

        Map<String, FlaskApi> flaskApiMap = loadFlaskApiMap();

        for (Map.Entry<String, FlaskApi> flaskApiEntry : flaskApiMap.entrySet()) {
            FlaskApi flaskApi = flaskApiEntry.getValue();
            try {
                flaskApi.addClient(addRequest);
            } catch (Exception e) {
                log.error("[XrayBiz-addClient] Exception", e);
                throw new BizException();
            }
        }


        return true;

    }

    public Boolean deleteClient(String email) {

        RemoveRequest removeRequest = new RemoveRequest();
        removeRequest.setEmail(email);

        Map<String, FlaskApi> flaskApiMap = loadFlaskApiMap();

        for (Map.Entry<String, FlaskApi> flaskApiEntry : flaskApiMap.entrySet()) {
            FlaskApi flaskApi = flaskApiEntry.getValue();
            try {
                flaskApi.removeClient(removeRequest);
            } catch (Exception e) {
                log.error("[XrayBiz-addClient] Exception", e);
                throw new BizException();
            }
        }

        return true;

    }

    public void syncClient(List<Client> clientList, String serverName) {

        log.info("[syncClient] clientList=[{}], serverName=[{}]", clientList, serverName);

        SyncRequest syncRequest = new SyncRequest();
        syncRequest.setClients(clientList);

        Map<String, FlaskApi> flaskApiMap = loadFlaskApiMap();

        if (StringUtils.isNotBlank(serverName)) {
            log.info("[syncClient] sync server: {}", serverName);
            FlaskApi flaskApi = flaskApiMap.get(serverName);
            if (flaskApi == null) {
                throw new BizException("服务器不存在");
            }
            Response response = flaskApi.syncClients(syncRequest);
            log.info("[syncClient] sync response: {}", response);
            return;
        }

        for (Map.Entry<String, FlaskApi> flaskApiEntry : flaskApiMap.entrySet()) {
            FlaskApi flaskApi = flaskApiEntry.getValue();
            try {
                flaskApi.syncClients(syncRequest);
            } catch (Exception e) {
                log.error("[XrayBiz-syncClient] Exception", e);
                throw new BizException();
            }
        }

    }

}
