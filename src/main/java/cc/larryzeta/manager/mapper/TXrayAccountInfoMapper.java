package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TXrayAccountInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TXrayAccountInfoMapper {
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
    int insert(TXrayAccountInfo row);

    /**
    * insertSelective
    * @param row row
    * @return int int
    */
    int insertSelective(TXrayAccountInfo row);

    /**
    * selectByPrimaryKey
    * @param id id
    * @return TXrayAccountInfo TXrayAccountInfo
    */
    TXrayAccountInfo selectByPrimaryKey(String id);

    /**
    * updateByPrimaryKeySelective
    * @param row row
    * @return int int
    */
    int updateByPrimaryKeySelective(TXrayAccountInfo row);

    /**
    * updateByPrimaryKey
    * @param row row
    * @return int int
    */
    int updateByPrimaryKey(TXrayAccountInfo row);
}