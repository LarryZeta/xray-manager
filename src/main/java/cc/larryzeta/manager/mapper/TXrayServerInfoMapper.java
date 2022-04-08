package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TXrayServerInfo;

import java.util.List;

public interface TXrayServerInfoMapper {
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
    int insert(TXrayServerInfo row);

    /**
    * insertSelective
    * @param row row
    * @return int int
    */
    int insertSelective(TXrayServerInfo row);

    /**
    * selectByPrimaryKey
    * @param id id
    * @return TXrayServerInfo TXrayServerInfo
    */
    TXrayServerInfo selectByPrimaryKey(String id);

    /**
     * selectAll
     * @return List<TXrayServerInfo> List<TXrayServerInfo>
     */
    List<TXrayServerInfo> selectAll();

    /**
    * updateByPrimaryKeySelective
    * @param row row
    * @return int int
    */
    int updateByPrimaryKeySelective(TXrayServerInfo row);

    /**
    * updateByPrimaryKey
    * @param row row
    * @return int int
    */
    int updateByPrimaryKey(TXrayServerInfo row);
}