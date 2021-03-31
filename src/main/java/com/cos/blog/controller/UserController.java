package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 로 한다.
// 그냥 주소가 /이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/**, /image/** 허용

@Controller
public class UserController {
    //회원가입
    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }
    // 로그인
    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
}
