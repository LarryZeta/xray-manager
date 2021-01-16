package cc.larryzeta.manager.compoment;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
        if (isAdmin) {
            return true;
        } else {
            response.sendRedirect("/");
            return false;
        }

    }
}
