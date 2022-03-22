package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TPlanInfo;

public interface TPlanInfoMapper {
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
    int insert(TPlanInfo row);

    /**
    * insertSelective
    * @param row row
    * @return int int
    */
    int insertSelective(TPlanInfo row);

    /**
    * selectByPrimaryKey
    * @param id id
    * @return TPlanInfo TPlanInfo
    */
    TPlanInfo selectByPrimaryKey(String id);

    /**
    * updateByPrimaryKeySelective
    * @param row row
    * @return int int
    */
    int updateByPrimaryKeySelective(TPlanInfo row);

    /**
    * updateByPrimaryKey
    * @param row row
    * @return int int
    */
    int updateByPrimaryKey(TPlanInfo row);
}