package com.sparta.mydelivery.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) //@Secured 활성화
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
        http.csrf()
                .disable();
        http.authorizeRequests()
// 어떤 요청이든 '인증'
//                .anyRequest().authenticated()
//                 회원 관리 처리 API 전부를 login 없이 허용
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/**").permitAll()
                .and()
// 로그인 기능 허용
                .formLogin()
//                .defaultSuccessUrl("/")
//                .loginPage("/user/login")
//                // 로그인 처리 (POST /login)
                .loginProcessingUrl("/user/login")

                .permitAll()
                .and()
//// 로그아웃 기능 허용
                .logout()
                .logoutUrl("/user/logout")
                .permitAll();
    }
}
