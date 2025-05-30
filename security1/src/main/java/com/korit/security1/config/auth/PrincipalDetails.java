package com.korit.security1.config.auth;

import com.korit.security1.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

//시큐리티가  /login 주소 요청이 오면 낚아채서 로그인을 진행 시킨다.
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다. => security contextHolder
// 오브 젝트 => Authentication 타입 객테
// Authentication안에 User정보가 있어야 됨
// User오브젝트 타입  => 유저 Details 타입 객체
// security session => Authentication => UserDetails(PrincipalDetails)
@Data
@NoArgsConstructor
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user;// 콤 포지션
    private Map<String, Object> attributes;

    //일반 로그인 할때 쓰이는 생성자
    public PrincipalDetails(User user){
        this.user = user;
    }



    // Oaith로그인 할때 쓰이는 생성자
    public PrincipalDetails(User user, Map<String,Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    //해당 User의 권한을 리턴하는곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection =  new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return null;
    }

    //비밀번호 리턴
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //유저 이름 리턴
    @Override
    public String getUsername() {

        return null;
    }

    // 계정 만료 되었는가 여부 물어봄
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    // 우리 사이트는 ~~기간동안 로그인 접속기록이 없으면 휴먼 계정이라고 함
    public boolean isEnabled() {
        user.getLoginDate();
        //현재 시간  -  로그인 시간 => 1년을 초과하면 휴먼 계정
        return true;
    }

    @Override
    public String getName() {
        return "";
    }
}
