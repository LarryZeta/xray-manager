package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TXrayServerInfo;
import cc.larryzeta.manager.entity.TXrayServerInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TXrayServerInfoMapper {
    long countByExample(TXrayServerInfoExample example);

    int deleteByExample(TXrayServerInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TXrayServerInfo record);

    int insertSelective(TXrayServerInfo record);

    List<TXrayServerInfo> selectByExample(TXrayServerInfoExample example);

    TXrayServerInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TXrayServerInfo record, @Param("example") TXrayServerInfoExample example);

    int updateByExample(@Param("record") TXrayServerInfo record, @Param("example") TXrayServerInfoExample example);

    int updateByPrimaryKeySelective(TXrayServerInfo record);

    int updateByPrimaryKey(TXrayServerInfo record);
}