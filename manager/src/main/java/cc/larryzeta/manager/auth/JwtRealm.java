package cc.larryzeta.manager.auth;

import cc.larryzeta.manager.biz.UserBiz;
import cc.larryzeta.manager.config.JwtConfig;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.service.UserService;
import cc.larryzeta.manager.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = principals.toString().split("-")[0];
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(userService.getRoleList(username));
        simpleAuthorizationInfo.addStringPermissions(userService.getPermissions(username));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String token = (String) authenticationToken.getCredentials();
        String userName = null;
        String email = null;

        try {
            userName = JwtUtil.getClaimField(token, JwtUtil.USERNAME);
            email = JwtUtil.getClaimField(token, JwtUtil.EMAIL);

            TUserBaseInfo userBaseInfo = userBiz.getUserByEmail(email);

            if (userBaseInfo == null) {
                log.warn("user not found");
                return null;
            }

            try {
                JwtUtil.verify(token, userName, email, jwtConfig);
            } catch (Exception e) {
                log.warn("doGetAuthenticationInfo token incorrect", e);
                return null;
            }

        } catch (Exception e) {
            log.error("doGetAuthenticationInfo unknown Exception e", e);
        }

        return new SimpleAuthenticationInfo(userName + "-" + email, token, getName());
    }

}
