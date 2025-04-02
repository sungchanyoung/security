package com.korit.security1.oauth;

import com.korit.security1.config.auth.PrincipalDetails;
import com.korit.security1.model.User;
import com.korit.security1.oauth.provider.FaceBookUserInfo;
import com.korit.security1.oauth.provider.GoogleUserInfo;
import com.korit.security1.oauth.provider.NaverUserInfo;
import com.korit.security1.oauth.provider.OAuthUserInfo;
import com.korit.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
// 쇼셯 로그인 서비스 커스텀
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    @Autowired // 해당 메서드의 리턴되는 오브젝트를 Ioc로 등록해준다.
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    // 구글로 부터 받은 UserRequest 데이터에 대한 후처리 함수
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    public OAuth2User loadUser(OAuth2UserRequest userRequest){

        // getAttribute에 accessToken이 다 들어 가있음
        System.out.println("userRequrst: "+ userRequest.getAccessToken());
        System.out.println(userRequest.getAdditionalParameters());
        System.out.println(userRequest.getClientRegistration());// registrationId로 어떤 oauth로 로그인
        //구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료-> 코드를 리턴 (Oauth-client 라이브러리) -> AccessToken 요청
        // userRequest정보 -> loadUser함수 호출->구글로 부터  회원 프로필 받아준다.
        System.out.println(super.loadUser(userRequest).getAttributes());
        //회원 가입을 강제로 진행해볼 에정
        OAuth2User oAuth2User =  super.loadUser(userRequest);

        //회원가입을 강제 진행
        OAuthUserInfo oAuthUserInfo =null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            oAuthUserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            System.out.println("페이스북 로그인 요청 ");
            oAuthUserInfo = new FaceBookUserInfo(oAuth2User.getAttributes());
        }
        else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            System.out.println("네이버 로그인 요청");
            // 네이버는 reponse안에 정보들이 있어서
            oAuthUserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        }else{
            System.out.println("우리는 페이스북 네이버 구글만 지원합니다.");
        }

        //구글
        String provider = oAuthUserInfo.getProvider();
        //페이스북 로그인 했을때 왜 null이 나왔을까? =>facebook에서는 sub가 없기 때문에
        String provideId = oAuthUserInfo.getProviderId();
        String email = oAuth2User.getAttribute("email");
        String username =  provider + "_" + provideId;
        String password = bCryptPasswordEncoder.encode("겟인데어");
        String role = "Role_user";

        User user = userRepository.findByUsername(username);

        if(user == null){
            user =  User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(provideId)
                    .build();
            userRepository.save(user);
        }
        return new PrincipalDetails(user,oAuth2User.getAttributes());
    }
}
