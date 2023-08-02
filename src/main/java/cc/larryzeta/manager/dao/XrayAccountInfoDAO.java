package cc.larryzeta.manager.dao;

import cc.larryzeta.manager.entity.TXrayAccountInfo;
import cc.larryzeta.manager.entity.TXrayAccountInfoExample;
import cc.larryzeta.manager.enumeration.ReturnCodeEnum;
import cc.larryzeta.manager.exception.BizException;
import cc.larryzeta.manager.mapper.TXrayAccountInfoMapper;
import cc.larryzeta.manager.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

            if (xrayAccountInfo.getUserId() != null) {
                criteria.andUserIdEqualTo(xrayAccountInfo.getUserId());
            }

            return xrayAccountInfoMapper.selectByExample(example);

        } catch (Exception e) {
            log.error("[XrayAccountInfoDAO-getTXrayAccountInfo] error Exception:", e);
            throw e;
        }

    }

    public void updateTXrayAccountInfo(TXrayAccountInfo xrayAccountInfo) {

        log.info("[updateTXrayAccountInfo] xrayAccountInfo=[{}]", JsonUtils.toJSONString(xrayAccountInfo));

        try {

            if (xrayAccountInfo == null || xrayAccountInfo.getId() == null) {
                throw new BizException(ReturnCodeEnum.EXCEPTION.code, "deleteTXrayAccountInfo no condition");
            }

            xrayAccountInfoMapper.updateByPrimaryKeySelective(xrayAccountInfo);

        } catch (Exception e) {
            log.error("[XrayAccountInfoDAO-updateTXrayAccountInfo] error Exception:", e);
            throw e;
        }

    }

    public void deleteTXrayAccountInfo(TXrayAccountInfo xrayAccountInfo) {

        log.info("[deleteTXrayAccountInfo] xrayAccountInfo=[{}]", JsonUtils.toJSONString(xrayAccountInfo));

        try {

            if (xrayAccountInfo == null || xrayAccountInfo.getId() == null) {
                throw new BizException(ReturnCodeEnum.EXCEPTION.code, "deleteTXrayAccountInfo no condition");
            }

            xrayAccountInfoMapper.deleteByPrimaryKey(xrayAccountInfo.getId());

        } catch (Exception e) {
            log.error("[XrayAccountInfoDAO-deleteTXrayAccountInfo] error Exception:", e);
            throw e;
        }
    }


}
