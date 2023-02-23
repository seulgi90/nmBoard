package com.nmBoard.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nmBoard.test.service.MemberService;

@Configuration
@EnableWebSecurity //웹보안 활성화를위한 annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private MemberService memberService;
	
	// 비밀번호를 복호화/암호화하는 로직이 담긴 객체를 Bean으로 등록
    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // WebSecurity는 FilterChainProxy를 생성하는 필터
    // 위 설정을 통해 Spring Security에서 해당 요청은 인증 대상에서 제외
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.authorizeRequests()
			        .antMatchers("/member/**").authenticated()
			        .antMatchers("/admin/**").authenticated()
			        .antMatchers("/**").permitAll()
			    	
//    	http
//			    	.authorizeRequests()
//			        .anyRequest().authenticated()
       .and()
             .formLogin() // Form 기반의 로그인인 경우
		            .loginPage("/login") // 사용자 정의 로그인 페이지: 로그인에 사용할 화면 지정
		            .defaultSuccessUrl("/loginSuccess") //로그인 성공 후 이동 페이지
		            .failureUrl("/loginFail")// 로그인 실패 후 이동 페이지
		            .usernameParameter("id") //아이디 파라미터명 설정
		            .passwordParameter("password") //패스워드 파라미터명 설정
		            .loginProcessingUrl("/login")//로그인을 처리할 url을 설정한다. default값은 "/login" 이다. <form> 태그의 action속성과 맞추어준다.
		            .permitAll() //사용자 정의 로그인 페이지 접근 권한 승인
    	.and()
			    	.logout()
			        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			        .logoutSuccessUrl("/login")
			        .invalidateHttpSession(true);
    	
    	  http.exceptionHandling()
          			.accessDeniedPage("/denied");
    
       
    	http.csrf().disable(); // RESTfull을 사용하기 위해서는 csrf 기능을 비활성화해야 함   	

    }
    
    // AuthenticationManager: 사용자 인증을 담당
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
    

}

