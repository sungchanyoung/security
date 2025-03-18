package com.korit.security1.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvxConfig implements WebMvcConfigurer {

    @Override//Mustache를 재설정 할수 있다 오버라이드를 통해
    public void configureViewResolvers(ViewResolverRegistry registry){
        MustacheViewResolver resolver =  new MustacheViewResolver();
        resolver.setCharset("UTF-8");
        resolver.setContentType("text/html; charset = UTF-8");
        resolver.setPrefix("classPath:/templates/");
        resolver.setSuffix(".html");

        registry.viewResolver(resolver);
    }
}
