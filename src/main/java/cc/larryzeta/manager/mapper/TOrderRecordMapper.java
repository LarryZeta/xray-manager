package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TOrderRecord;
import cc.larryzeta.manager.entity.TOrderRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOrderRecordMapper {
    long countByExample(TOrderRecordExample example);

    int deleteByExample(TOrderRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TOrderRecord record);

    int insertSelective(TOrderRecord record);

    List<TOrderRecord> selectByExample(TOrderRecordExample example);

    TOrderRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TOrderRecord record, @Param("example") TOrderRecordExample example);

    int updateByExample(@Param("record") TOrderRecord record, @Param("example") TOrderRecordExample example);

    int updateByPrimaryKeySelective(TOrderRecord record);

    int updateByPrimaryKey(TOrderRecord record);
}