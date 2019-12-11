/**
 * @program: bbs
 * @description: 配置类
 * @author: Wu
 * @create: 2019-12-10 20:55
 **/
package com.wu.bbs.config;

import com.wu.bbs.interceptors.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/css/**").excludePathPatterns("/js/**").excludePathPatterns("/fontawesome/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**","/js/**","/fontawesome/**").addResourceLocations("classpath:/static/css/","classpath:/static/js/","classpath:/static/fontawesome/");
    }
}
