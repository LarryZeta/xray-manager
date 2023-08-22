package cc.larryzeta.manager.api.auth.model;

import cc.larryzeta.manager.entity.TUserBaseInfo;
import lombok.Data;

import java.util.List;

@Data
public class AuthResponse {

    private String token;

    private TUserBaseInfo userBaseInfo;

    private List<String> roleList;

}
