package cc.larryzeta.manager.auth;

import cc.larryzeta.manager.dao.UserBaseInfoDAO;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.entity.User;
import cc.larryzeta.manager.service.UserService;
import cc.larryzeta.manager.util.JwtUtil;
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
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserBaseInfoDAO userBaseInfoDAO;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = principals.toString();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(userService.getRoleList(username));
        simpleAuthorizationInfo.addStringPermissions(userService.getPermissions(username));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String userName = null;
        try {
            userName = JwtUtil.getClaimField(token, JwtUtil.ACCOUNT);
//            User user = userService.getUserByName(userName);
            TUserBaseInfo query = new TUserBaseInfo();
            query.setUserName(userName);
            TUserBaseInfo userBaseInfo = userBaseInfoDAO.getTUserBaseInfo(query).get(0);
            if (userBaseInfo == null) {
                System.out.println("用户不存在");
                return null;
            }

            boolean verify = JwtUtil.verify(token, userName, userBaseInfo.getPasswd());
            if (!verify) {
                System.out.println("Token校验不正确");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName,token,getName());
        return authenticationInfo;
    }

}
