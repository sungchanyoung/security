package com.korit.security1.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    // 구글로 부터 받은 UserRequest 데이터에 대한 후처리 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest){
        // getAttribute에 accessToken이 다 들어 가있음
        System.out.println("userRequrst: "+ userRequest.getAccessToken());
        System.out.println(userRequest.getAdditionalParameters());
        System.out.println(userRequest.getClientRegistration());
        System.out.println(super.loadUser(userRequest).getAttributes());
        OAuth2User oAuth2User =  super.loadUser(userRequest);
        return oAuth2User;
    }
}
