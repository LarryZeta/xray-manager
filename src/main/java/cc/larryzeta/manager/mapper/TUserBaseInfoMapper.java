package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TUserBaseInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TUserBaseInfoMapper {
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
    int insert(TUserBaseInfo row);

    /**
    * insertSelective
    * @param row row
    * @return int int
    */
    int insertSelective(TUserBaseInfo row);

    /**
    * selectByPrimaryKey
    * @param id id
    * @return TUserBaseInfo TUserBaseInfo
    */
    TUserBaseInfo selectByPrimaryKey(String id);

    /**
     * selectByEmail
     * @param email email
     * @return TUserBaseInfo TUserBaseInfo
     */
    TUserBaseInfo selectByEmail(String email);

    List<TUserBaseInfo> selectAll();

    /**
    * updateByPrimaryKeySelective
    * @param row row
    * @return int int
    */
    int updateByPrimaryKeySelective(TUserBaseInfo row);

    /**
    * updateByPrimaryKey
    * @param row row
    * @return int int
    */
    int updateByPrimaryKey(TUserBaseInfo row);
}