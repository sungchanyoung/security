package com.korit.jwt.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);// 내서바가 응답할떄 json을 자바스크립트에서 처리할수 할지 설정하는것
        config.addAllowedOrigin("*");//모든 ip의 응답을 허용
        config.addAllowedHeader("*");//모든 header에 응답을 허용하겠다
        config.addAllowedMethod("*");//모든 httpmrthod를 허용
        source.registerCorsConfiguration("/api/**",config);
        return new CorsFilter(source);
    }
}
