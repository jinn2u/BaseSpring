package com.cos.blog.service;

import com.cos.blog.Repository.UserRepository;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//서비스가 필요한 이유
// 트랜잭션 관리 =>
// ************   서비스의 의미  **************
// 서비스(데이터베이스의 변경) 이 실패하였을경우 rollback을 할 수 있다.

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해준다 IOC를 해준다.
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void SignUp(User user) {
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void modifyMember(User user){
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException(("회원 찾기 실패"));
        });
        persistance.setPassword(encoder.encode(user.getPassword()));
        persistance.setEmail(user.getEmail());
    }
}
