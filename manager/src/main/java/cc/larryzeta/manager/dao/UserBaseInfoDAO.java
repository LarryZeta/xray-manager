package cc.larryzeta.manager.dao;

import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.entity.TUserBaseInfoExample;
import cc.larryzeta.manager.mapper.TUserBaseInfoMapper;
import cc.larryzeta.manager.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * ProjectName xray-manager
 * ClassName TUserBaseInfoDao
 * Date 3/1/2022 14:37
 *
 * @author Larry
 * @description
 */

@Slf4j
@Repository
public class UserBaseInfoDAO {

    @Resource
    private TUserBaseInfoMapper tUserBaseInfoMapper;

    public TUserBaseInfo getTUserBaseInfoById(int id) {

        log.info("[TUserBaseInfoDao-getTUserBaseInfoById] id=[{}]", id);
        try {
            return tUserBaseInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-getTUserBaseInfoByEmail] error Exception:", e);
            throw e;
        }

    }

    public List<TUserBaseInfo> getTUserBaseInfo(TUserBaseInfo userBaseInfo) {

        log.info("[TUserBaseInfoDao-getTUserBaseInfo] userBaseInfo=[{}]", JsonUtils.toJSONString(userBaseInfo));
        try {
            TUserBaseInfoExample example = new TUserBaseInfoExample();
            TUserBaseInfoExample.Criteria criteria = example.createCriteria();

            if (userBaseInfo == null) {
                return tUserBaseInfoMapper.selectByExample(example);
            }

            if (userBaseInfo.getId() != null) {
                criteria.andIdEqualTo(userBaseInfo.getId());
            }
            if (StringUtils.hasText(userBaseInfo.getEmail())) {
                criteria.andEmailEqualTo(userBaseInfo.getEmail());
            }
            if (StringUtils.hasText(userBaseInfo.getUserName())) {
                criteria.andUserNameEqualTo(userBaseInfo.getUserName());
            }
            if (StringUtils.hasText(userBaseInfo.getUserStatus())) {
                criteria.andUserStatusEqualTo(userBaseInfo.getUserStatus());
            }

            return tUserBaseInfoMapper.selectByExample(example);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-getTUserBaseInfoByEmail] error Exception:", e);
            throw e;
        }

    }

    public void saveTUserBaseInfo(TUserBaseInfo tUserBaseInfo) {

        log.info("[TUserBaseInfoDao-saveTUserBaseInfo] tUserBaseInfo=[{}]", tUserBaseInfo);

        try {
            tUserBaseInfoMapper.insertSelective(tUserBaseInfo);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-saveTUserBaseInfo] error Exception:", e);
            throw e;
        }
    }

    public void updateTUserBaseInfo(TUserBaseInfo userBaseInfo) {

        log.info("[TUserBaseInfoDao-updateTUserBaseInfo] userBaseInfo=[{}]", userBaseInfo);

        try {
            tUserBaseInfoMapper.updateByPrimaryKeySelective(userBaseInfo);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-saveTUserBaseInfo] error Exception:", e);
            throw e;
        }
    }


}
