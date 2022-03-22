package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TUserRoleRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface TUserRoleRelationMapper {
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
    int insert(TUserRoleRelation row);

    /**
    * insertSelective
    * @param row row
    * @return int int
    */
    int insertSelective(TUserRoleRelation row);

    /**
    * selectByPrimaryKey
    * @param id id
    * @return TUserRoleRelation TUserRoleRelation
    */
    TUserRoleRelation selectByPrimaryKey(String id);

    /**
    * updateByPrimaryKeySelective
    * @param row row
    * @return int int
    */
    int updateByPrimaryKeySelective(TUserRoleRelation row);

    /**
    * updateByPrimaryKey
    * @param row row
    * @return int int
    */
    int updateByPrimaryKey(TUserRoleRelation row);
}