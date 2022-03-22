package cc.larryzeta.manager.dao;

import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.mapper.TUserBaseInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

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
    private TUserBaseInfoMapper TUserBaseInfoMapper;

    public TUserBaseInfo getTUserBaseInfoByEmail(String email) {

        log.info("[TUserBaseInfoDao-getTUserBaseInfoByEmail] email=[{}]", email);
        try {
            return TUserBaseInfoMapper.selectByEmail(email);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-getTUserBaseInfoByEmail] error Exception:", e);
            throw e;
        }

    }

    public void saveTUserBaseInfo(TUserBaseInfo tUserBaseInfo) {

        log.info("[TUserBaseInfoDao-saveTUserBaseInfo] tUserBaseInfo=[{}]", tUserBaseInfo);

        try {
            TUserBaseInfoMapper.insertSelective(tUserBaseInfo);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-saveTUserBaseInfo] error Exception:", e);
            throw e;
        }
    }


}
