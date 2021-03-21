package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest";

    //lombok Test 하기
    @GetMapping("/http/lombok")
    public String lombokTest(){
//        Member m = new Member(1, "jin", "1234", "email");
        Member m = Member.builder().username("jin").password("1234").email("email").build();
        System.out.println(TAG+ " getter "+ m.getUsername());
        m.setUsername("dong");
        System.out.println(TAG+ " setter " + m.getUsername());
        return "lombok Test 완료";
    }


    @GetMapping("/http/get")
    public String getTest(@RequestBody Member m){
        System.out.println(m.getUsername()+"sibal");
        return "get요청: "+ m.getId()+ ", "+ m.getUsername()+ ", "+ m.getEmail()+ ", "+ m.getPassword();
    }
}
