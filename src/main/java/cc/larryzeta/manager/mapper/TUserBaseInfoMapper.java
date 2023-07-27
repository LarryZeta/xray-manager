package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.entity.TUserBaseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserBaseInfoMapper {
    long countByExample(TUserBaseInfoExample example);

    int deleteByExample(TUserBaseInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserBaseInfo record);

    int insertSelective(TUserBaseInfo record);

    List<TUserBaseInfo> selectByExample(TUserBaseInfoExample example);

    TUserBaseInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserBaseInfo record, @Param("example") TUserBaseInfoExample example);

    int updateByExample(@Param("record") TUserBaseInfo record, @Param("example") TUserBaseInfoExample example);

    int updateByPrimaryKeySelective(TUserBaseInfo record);

    int updateByPrimaryKey(TUserBaseInfo record);
}