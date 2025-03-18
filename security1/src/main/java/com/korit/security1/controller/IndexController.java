package com.korit.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping({"","/"})
    public  String index(){
        //머스테치 => 간단한 템플릿 엔진, 로직 코드를 사용할 수 없어 View의 역활과 서버의 역할이 명확하게 분리
        //머스테치 기본 src/main/resource
        //뷰 리졸버 설정 :templates(prefix),.mustache(suffix) 생략 가능!!
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){

        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){

        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){

        return "manager";
    }

    //스프링 시큐리티 해당 주소를  낚아 채버려어요 -securityConfig 파일 생성 작동 안함
    @GetMapping("/login")
    public @ResponseBody String login(){

        return "login";
    }

    @GetMapping("/join")
    public @ResponseBody String join(){

        return "join";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc(){

        return "회원가입 완료 됨";
    }
}
