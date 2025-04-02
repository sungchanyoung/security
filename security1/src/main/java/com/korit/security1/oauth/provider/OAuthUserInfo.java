package com.korit.security1.oauth.provider;
// 이 파일을 만드는 이유 : 소셜 로그인에서 사용자 정보를 통합적으로 다루기 위한 인터페이스
//구글 :email,sub name을 반환  , 네이버 :  response.email, response.id
//목적1.공통 인터페이스 제공, 2.각 소셜 제공자별로 구현 클래스 분리 3.코드의 유연성과 확장성 증가
public interface OAuthUserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}
