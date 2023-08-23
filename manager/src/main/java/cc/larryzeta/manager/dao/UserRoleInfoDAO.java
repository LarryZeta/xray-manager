package cc.larryzeta.manager.dao;

import cc.larryzeta.manager.entity.TUserRoleInfo;
import cc.larryzeta.manager.entity.TUserRoleInfoExample;
import cc.larryzeta.manager.mapper.TUserRoleInfoMapper;
import cc.larryzeta.manager.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Repository
public class UserRoleInfoDAO {

    @Autowired
    private TUserRoleInfoMapper tUserRoleInfoMapper;

    public List<TUserRoleInfo> getUserRole(TUserRoleInfo userRoleInfo) {

        log.info("[getUserRole] userRoleInfo=[{}]", JsonUtils.toJSONString(userRoleInfo));

        try {
            TUserRoleInfoExample example = new TUserRoleInfoExample();
            TUserRoleInfoExample.Criteria criteria = example.createCriteria();

            if (userRoleInfo == null) {
                return tUserRoleInfoMapper.selectByExample(example);
            }

            if (userRoleInfo.getId() != null) {
                criteria.andIdEqualTo(userRoleInfo.getId());
            }
            if (userRoleInfo.getUserId() != null) {
                criteria.andUserIdEqualTo(userRoleInfo.getUserId());
            }
            if (StringUtils.hasText(userRoleInfo.getRoleCode())) {
                criteria.andRoleCodeEqualTo(userRoleInfo.getRoleCode());
            }
            if (StringUtils.hasText(userRoleInfo.getRoleName())) {
                criteria.andRoleNameEqualTo(userRoleInfo.getRoleName());
            }
            if (StringUtils.hasText(userRoleInfo.getRoleStatus())) {
                criteria.andRoleStatusEqualTo(userRoleInfo.getRoleStatus());
            }

            return tUserRoleInfoMapper.selectByExample(example);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-getTUserBaseInfoByEmail] error Exception:", e);
            throw e;
        }

    }

    public void save(TUserRoleInfo userRoleInfo) {
        log.info("[save] userRoleInfo=[{}]", JsonUtils.toJSONString(userRoleInfo));

        try {
            tUserRoleInfoMapper.insertSelective(userRoleInfo);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-getTUserBaseInfoByEmail] error Exception:", e);
            throw e;
        }
    }

}
