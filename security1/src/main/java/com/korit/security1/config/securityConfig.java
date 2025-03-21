package com.korit.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //활성화를 하면 => 스프링 시큐리티 필터가 스프링 피터 체인에 등록이 된다.
public class securityConfig{

    @Bean // 해당 메서드의 리턴되는 오브젝트를 Ioc로 등록해준다.
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (필요에 따라 설정)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").authenticated() // '/user/**' 경로는 로그인 필요
                        .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER") // 'ADMIN' 또는 'MANAGER' 권한 필요
                        .requestMatchers("/admin/**").hasRole("ADMIN") // 'ADMIN' 권한 필요
                        .anyRequest().permitAll() // 그 외 모든 요청은 인증 없이 접근 가능
                )
                .formLogin(form -> form
                        .loginPage("/loginForm") // 사용자 지정 로그인 페이지 설정
                        .loginProcessingUrl("/login") // /login 요청이 들어오면 시큐리티가 로그인 처리
                        .defaultSuccessUrl("/home", true) // 로그인 성공 시 이동할 페이지
                        .permitAll() // 로그인 페이지는 인증 없이 접근 가능
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/loginForm") // OAuth2 로그인 페이지 지정
                );

        return http.build();
    }




}
