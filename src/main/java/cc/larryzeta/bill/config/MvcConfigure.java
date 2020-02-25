package cc.larryzeta.bill.config;

import cc.larryzeta.bill.compoment.AdminHandlerInterceptor;
import cc.larryzeta.bill.compoment.LoginHandlerInterceptor;
import cc.larryzeta.bill.compoment.SessionHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigure implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("service");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/service").setViewName("service");
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // before login
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/user/login", "/user/register", "/css/**", "/js/**");
                // after login
                registry.addInterceptor(new SessionHandlerInterceptor()).addPathPatterns("/login", "/register", "/user/login");
                // isAdmin
                registry.addInterceptor(new AdminHandlerInterceptor()).addPathPatterns("/orders", "/clients", "/addClient", "/users", "/accounts");

            }

        };

    }

}
