package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration  //빈등록
@EnableWebSecurity //시큐리티 필터를 추가한다. => 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PrincipalDetailService principalDetailService;
    @Bean // IOC가 된다.
    public BCryptPasswordEncoder encodedPWD(){
        return new BCryptPasswordEncoder();
    }
    //  시큐리티가 password를 가로채 대신 로그인을 해주는데 해당 password가 어떤 해쉬로 되었는지 알아야한다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodedPWD());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() //csrf 토큰 비활성화
            .authorizeRequests()
                .antMatchers("/auth/**", "/js/**", "/css/**", "/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")        // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해준다.
                .defaultSuccessUrl("/");
    }
}
