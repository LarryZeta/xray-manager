package cc.larryzeta.manager.mapper;

import cc.larryzeta.manager.entity.TUserRoleInfo;
import cc.larryzeta.manager.entity.TUserRoleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserRoleInfoMapper {
    long countByExample(TUserRoleInfoExample example);

    int deleteByExample(TUserRoleInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserRoleInfo record);

    int insertSelective(TUserRoleInfo record);

    List<TUserRoleInfo> selectByExample(TUserRoleInfoExample example);

    TUserRoleInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserRoleInfo record, @Param("example") TUserRoleInfoExample example);

    int updateByExample(@Param("record") TUserRoleInfo record, @Param("example") TUserRoleInfoExample example);

    int updateByPrimaryKeySelective(TUserRoleInfo record);

    int updateByPrimaryKey(TUserRoleInfo record);
}