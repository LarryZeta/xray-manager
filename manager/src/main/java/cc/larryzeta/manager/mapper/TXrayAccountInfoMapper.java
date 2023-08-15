package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TXrayAccountInfo;
import cc.larryzeta.manager.entity.TXrayAccountInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TXrayAccountInfoMapper {
    long countByExample(TXrayAccountInfoExample example);

    int deleteByExample(TXrayAccountInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TXrayAccountInfo record);

    int insertSelective(TXrayAccountInfo record);

    List<TXrayAccountInfo> selectByExample(TXrayAccountInfoExample example);

    TXrayAccountInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TXrayAccountInfo record, @Param("example") TXrayAccountInfoExample example);

    int updateByExample(@Param("record") TXrayAccountInfo record, @Param("example") TXrayAccountInfoExample example);

    int updateByPrimaryKeySelective(TXrayAccountInfo record);

    int updateByPrimaryKey(TXrayAccountInfo record);
}