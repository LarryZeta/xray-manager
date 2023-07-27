package cc.larryzeta.manager.dao;

import cc.larryzeta.manager.entity.TXrayServerInfo;
import cc.larryzeta.manager.entity.TXrayServerInfoExample;
import cc.larryzeta.manager.mapper.TXrayServerInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Repository
public class XrayServerInfoDAO {

    @Resource
    private TXrayServerInfoMapper tXrayServerInfoMapper;

    public List<TXrayServerInfo> getAllXrayServerInfo() {

        log.info("[TUserBaseInfoDao-getAllXrayServerInfo]");
        try {
            TXrayServerInfoExample example = new TXrayServerInfoExample();
            return tXrayServerInfoMapper.selectByExample(example);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-getAllXrayServerInfo] error Exception:", e);
            throw e;
        }

    }


}
