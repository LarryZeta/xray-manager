package cc.larryzeta.manager.model;

import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.entity.TUserRoleInfo;
import cc.larryzeta.manager.entity.TXrayAccountInfo;
import lombok.Data;

import java.util.List;

@Data
public class UserBO {

    private TUserBaseInfo userBaseInfo;

    private List<TUserRoleInfo> userRoleInfoList;

}
