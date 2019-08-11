package cc.larryzeta.bill.compoment;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null) {
            request.setAttribute("msg", "Permission denied, log in first please.");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}
