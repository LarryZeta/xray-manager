package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TOrderRecord;

public interface TOrderRecordMapper {
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
    int insert(TOrderRecord row);

    /**
    * insertSelective
    * @param row row
    * @return int int
    */
    int insertSelective(TOrderRecord row);

    /**
    * selectByPrimaryKey
    * @param id id
    * @return TOrderRecord TOrderRecord
    */
    TOrderRecord selectByPrimaryKey(String id);

    /**
    * updateByPrimaryKeySelective
    * @param row row
    * @return int int
    */
    int updateByPrimaryKeySelective(TOrderRecord row);

    /**
    * updateByPrimaryKey
    * @param row row
    * @return int int
    */
    int updateByPrimaryKey(TOrderRecord row);
}