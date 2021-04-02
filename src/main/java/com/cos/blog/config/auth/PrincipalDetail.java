package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetail implements UserDetails {
    private User user;

    public PrincipalDetail(User user){
        this.user = user;
    }
    // 계정의 권한을 리턴한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()->{return "ROLE_"+user.getRole();});
        return collectors;
    }
    // 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진해앟고 완료가 되면 UserDetails 타입의 오브젝트를 스프링시큐리티의 고유한 세션 저장소에 저장을 해준다.
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 리턴한다.(true 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겼는지 안잠겼는지 확인한다(true 안잠김)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 리턴한다.(true 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화(사용가능)인지 리턴한다.(true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
