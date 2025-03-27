package com.korit.security1.config.auth;

import com.korit.security1.model.User;
import com.korit.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //시큐리티에서 설정에서 loginProcessingUrl("/login")
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 잇는 loadUserByUsername 함수가 실행

public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    //시큐리티 session  = (내부 Authentication(내부 User Details))
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity =  userRepository.findByUsername(username);
        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
