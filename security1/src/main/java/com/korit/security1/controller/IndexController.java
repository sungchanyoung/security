package com.korit.security1.controller;

import com.korit.security1.config.auth.PrincipalDetails;
import com.korit.security1.model.User;
import com.korit.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
        System.out.println("/test/login");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication "+ principalDetails.getUser());
        System.out.println("userDetails" + userDetails.getUser());
        return "새션 확인";
    }
    @Autowired
    private BCryptPasswordEncoder encoder;
    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
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

    @GetMapping("/join")
    public @ResponseBody String join(User user) {
        System.out.println(user);
        user.setRole("ROLE_USER");
        String raqPassowrd = user.getPassword();
        String encPassword  =encoder.encode(raqPassowrd);
        userRepository.save(user);
        return "redirect:/loginForm ";
    }
}
