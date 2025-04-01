package com.korit.security1.config;

import com.korit.security1.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //활성화를 하면 => 스프링 시큐리티 필터가 스프링 피터 체인에 등록이 된다.
public class securityConfig{

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

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
                        .defaultSuccessUrl("/", true) // 로그인 성공 시 이동할 페이지
                        .permitAll() // 로그인 페이지는 인증 없이 접근 가능
                )
                //구글 로그인이 완료된 뒤의 후처리가 필요함 1.코드 받기(인증),2.엑세스 토큰(권한)
                // 3.사용자 프로필 정보 가져오기
                // 그 정보 토대로 회원가입을 자동으로 진해시키기도 함
                // oauthclient 라이브러리 사용하면 => (엑세스 토큰 + 사용자 프로필 정보 )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/loginForm") // OAuth2 로그인 페이지 지정
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(principalOauth2UserService)  // 수정된 부분
                        )
                );

        return http.build();
    }




}
