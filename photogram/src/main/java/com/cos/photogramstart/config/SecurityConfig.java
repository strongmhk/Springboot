package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration // IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super 삭제 -> 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/","/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin() // 인증이 필요한 페이지 요청시 formLogin을 할거고, 그 formLogin 페이지가 /auth/signin 이다. 그리고 로그인 정상 처리시 /로 가게한다
                .loginPage("/auth/signin")
                .defaultSuccessUrl("/"); // antMatchers에 적혀있는 주소들만 인증이 필요하고 나머지 주소는 접근허용함.

        // http 300번대 코드는 모두 요청이 리다이렉션 됐다고 보면됨. 여기서 리다이렉션은 요청 재분배
    }


}
