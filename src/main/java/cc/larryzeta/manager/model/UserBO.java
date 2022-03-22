package cc.larryzeta.manager.model;

import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.entity.TUserRoleRelation;
import lombok.Data;

import java.util.List;

@Data
public class UserBO {

    private TUserBaseInfo tUserBaseInfo;

    private List<TUserRoleRelation> tUserRoleRelations;

}
