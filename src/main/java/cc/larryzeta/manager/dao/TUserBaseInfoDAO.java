package cc.larryzeta.manager.dao;

import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.exception.ReturnException;
import cc.larryzeta.manager.mapper.TUserBaseInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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
public class TUserBaseInfoDAO {

    @Resource
    private TUserBaseInfoMapper tUserBaseInfoMapper;

    public TUserBaseInfo getTUserBaseInfoById(String id) {

        log.info("[TUserBaseInfoDao-getTUserBaseInfoById] id=[{}]", id);
        try {
            return tUserBaseInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-getTUserBaseInfoByEmail] error Exception:", e);
            throw e;
        }

    }

    public TUserBaseInfo getTUserBaseInfoByEmail(String email) {

        log.info("[TUserBaseInfoDao-getTUserBaseInfoByEmail] email=[{}]", email);
        try {
            return tUserBaseInfoMapper.selectByEmail(email);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-getTUserBaseInfoByEmail] error Exception:", e);
            throw e;
        }

    }

    public List<TUserBaseInfo> getAllTUserBaseInfo() {
        log.info("[TUserBaseInfoDao-getTUserBaseInfo]");
        try {
            return tUserBaseInfoMapper.selectAll();
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
