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
        //api 서버에 다른출처에서 접근할 수 있게 허용해주는 것으로 f와b가 분리된 프로젝트에서 흔히 사용됩니다.
        //cors 설정을 등록할 수 있는 객체를 생성
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //CorsConfiguration 객체를 통해 구체적인 설정을 구성합니다.
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내서바가 응답할떄 json을 자바스크립트에서 처리할수 할지 설정하는것
        config.addAllowedOrigin("*"); // 모든 ip의 응답을 허용
        config.addAllowedHeader("*"); // 모든 header에 응답을 허용하겠다
        config.addAllowedMethod("*"); // 모든 http method를 허용
        source.registerCorsConfiguration("/api/**",config);
        return new CorsFilter(source);
    }
}
