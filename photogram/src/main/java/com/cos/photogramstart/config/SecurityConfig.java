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
        // super.configure(http); // super 삭제 -> 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
        http.csrf().disable();
        http.authorizeRequests()// 이 주소 경로로 요청이 들어오면
                .antMatchers("/","/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**")
                .authenticated()// 인증이 필요하다.
                .anyRequest() // 그 외의 요청들은
                .permitAll() // 모두 허용한다.
                .and() // 그리고
                .formLogin() // 로그인(인증)이 필요한 페이지 요청시
                .loginPage("/auth/signin")// 로그인 페이지 auth/signin 으로 이동시키고 (GET), GET으로 요청오면 로그인 페이지로 이동
                .loginProcessingUrl("/auth/signin") // POST로 요청오면 -> 스프링 시큐리티가 로그인 프로세스 진행(스프링 시큐리티에게 로그인 프로세스 위임)
                .defaultSuccessUrl("/"); // 인증이 정상적으로 완료되면 / 로 이동

        // http 300번대 코드는 모두 요청이 리다이렉션 됐다고 보면됨. 여기서 리다이렉션은 요청 재분배
    }


}
