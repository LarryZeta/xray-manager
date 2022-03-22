package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TUserRoleInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TUserRoleInfoMapper {
    /**
    * deleteByPrimaryKey
    * @param id id
    * @return int int
    */
    int deleteByPrimaryKey(String id);

    /**
    * insert
    * @param row row
    * @return int int
    */
    int insert(TUserRoleInfo row);

    /**
    * insertSelective
    * @param row row
    * @return int int
    */
    int insertSelective(TUserRoleInfo row);

    /**
    * selectByPrimaryKey
    * @param id id
    * @return TUserRoleInfo TUserRoleInfo
    */
    TUserRoleInfo selectByPrimaryKey(String id);

    /**
    * updateByPrimaryKeySelective
    * @param row row
    * @return int int
    */
    int updateByPrimaryKeySelective(TUserRoleInfo row);

    /**
    * updateByPrimaryKey
    * @param row row
    * @return int int
    */
    int updateByPrimaryKey(TUserRoleInfo row);
}