package com.korit.security1.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
// 쇼셯 로그인 서비스 커스텀
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    // 구글로 부터 받은 UserRequest 데이터에 대한 후처리 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest){
        // getAttribute에 accessToken이 다 들어 가있음
        System.out.println("userRequrst: "+ userRequest.getAccessToken());
        System.out.println(userRequest.getAdditionalParameters());
        System.out.println(userRequest.getClientRegistration());// registrationId로 어떤 oauth로 로그인
        //구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료->code를 리턴 (Oauth-client 라이브러리) -> AccessToken 요청
        // userRequest정보 -> loadUser함수 호출->구글로 부터  회원 프로필 받아준다.
        System.out.println(super.loadUser(userRequest).getAttributes());
        //회원 가입을 강제로 진행해볼 에정
        OAuth2User oAuth2User =  super.loadUser(userRequest);
        return oAuth2User;
    }
}
