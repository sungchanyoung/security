package com.korit.security1.controller;

import com.korit.security1.config.auth.PrincipalDetails;
import com.korit.security1.model.User;
import com.korit.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test/login")
    public @ResponseBody String loginTest(Authentication authentication,
                                          //세션 정보를 가질수 있다.
                                          @AuthenticationPrincipal PrincipalDetails userDetails){

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication "+ principalDetails.getUser());
        System.out.println("userDetails" + userDetails.getUser());
        return "새션 확인";
    }
    @Autowired
    private BCryptPasswordEncoder encoder;
    @GetMapping({"", "/"})
    public String index() {
        //머스터체 기본 폴더 src/main/resources/
        //뷰 리졸버 설정 : templates(prefix),.mustache(suffix)생략 가능
        return "index"; // src/main/resources/templates/index.mustache
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    //oauth로그인 PrincipalDetails타입으로 받아지고
    //일반 로그인 해도 PrincipalDetails 받아진다.
    //@AuthenticationPrincipal 언제 활성화 되냐? 기본적으로 loadUser 활성화
    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("principalDetails " + principalDetails.getUser());
        return "user";
    }

    @GetMapping("/join")
    public @ResponseBody String join(User user) {
        user.setRole("ROLE_USER");
        String raqPassword = user.getPassword();
        String encPassword  =encoder.encode(raqPassword);
        userRepository.save(user);
        return "redirect:/loginForm ";
    }
}
