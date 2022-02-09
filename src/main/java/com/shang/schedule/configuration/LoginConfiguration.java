package com.shang.schedule.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：Shang
 * @date ：Created at 0009 2022/2/9 21:54
 * @description：
 * @version:
 */
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new Interceptor());
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "/",            //登录
                "/login",            //登录
                "/favicon.ico",
                "/**/error",
                "/**/*.html",            //html静态资源
                "/static/**"             //静态资源
//                "/**/*.css",             //css静态资源
//                "/**/*.woff",
//                "/**/*.woff2",
//                "/**/*.ttf"
        );
    }

    /**
     * 解决resources下面静态资源无法访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico")//favicon.ico
                .addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
