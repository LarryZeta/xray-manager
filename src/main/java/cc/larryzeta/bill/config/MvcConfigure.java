package cc.larryzeta.bill.config;

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
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/register.html").setViewName("register");
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // before login
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/user/login", "/css/**", "/js/**", "/webjars/**");
                // after login
                registry.addInterceptor(new SessionHandlerInterceptor()).addPathPatterns("/login", "/register", "/user/login");
            }

        };

    }

}
