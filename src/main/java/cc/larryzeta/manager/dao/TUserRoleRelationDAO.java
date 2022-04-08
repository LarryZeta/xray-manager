package cc.larryzeta.manager.dao;

import cc.larryzeta.manager.entity.TUserRoleRelation;
import cc.larryzeta.manager.mapper.TUserRoleRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TUserRoleRelationDAO {

    @Autowired
    private TUserRoleRelationMapper userRoleRelationMapper;

    public List<TUserRoleRelation> getUserRole(String userId) {

        log.info("[getUserRole] userId=[{}]", userId);
        try {
            return userRoleRelationMapper.selectByUserId(userId);
        } catch (Exception e) {
            log.error("[TUserBaseInfoDao-getTUserBaseInfoByEmail] error Exception:", e);
            throw e;
        }

    }

}
