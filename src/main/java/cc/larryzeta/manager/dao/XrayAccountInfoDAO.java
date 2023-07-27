package cc.larryzeta.manager.dao;

import cc.larryzeta.manager.entity.TXrayAccountInfo;
import cc.larryzeta.manager.entity.TXrayAccountInfoExample;
import cc.larryzeta.manager.mapper.TXrayAccountInfoMapper;
import cc.larryzeta.manager.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Repository
public class XrayAccountInfoDAO {

    @Resource
    private TXrayAccountInfoMapper xrayAccountInfoMapper;

    public List<TXrayAccountInfo> getTXrayAccountInfo(TXrayAccountInfo xrayAccountInfo) {

        log.info("[getTXrayAccountInfo] xrayAccountInfo=[{}]", JsonUtils.toJSONString(xrayAccountInfo));

        try {

            TXrayAccountInfoExample example = new TXrayAccountInfoExample();
            TXrayAccountInfoExample.Criteria criteria = example.createCriteria();

            if (xrayAccountInfo == null) {
                return xrayAccountInfoMapper.selectByExample(example);
            }



            return xrayAccountInfoMapper.selectByExample(example);
        } catch (Exception e) {
            log.error("[XrayAccountInfoDAO-getTXrayAccountInfo] error Exception:", e);
            throw e;
        }

    }


}
