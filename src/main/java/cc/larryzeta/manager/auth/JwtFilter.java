package cc.larryzeta.manager.auth;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends AccessControlFilter {

    public static String ACCESS_TOKEN = "Access-Token";

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        // 解决跨域问题
        if(HttpMethod.OPTIONS.toString().matches(req.getMethod())) {
            return true;
        }
        if (isLoginAttempt(request, response)) {
            JwtToken token = new JwtToken(req.getHeader(ACCESS_TOKEN));
            try {
                getSubject(request, response).login(token);
                return true;
            } catch (Exception e) {
            }
        }
        onLoginFail(response);
        return false;
    }

    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(ACCESS_TOKEN);
        return authorization != null;
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.getWriter().write("login fail");
    }


}
