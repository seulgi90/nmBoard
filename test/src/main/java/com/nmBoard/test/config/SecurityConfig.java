package com.nmBoard.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nmBoard.test.service.SecurityService;

@Configuration
@EnableWebSecurity //웹보안 활성화를위한 annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private SecurityService securityService;
	
	/* DaoAuthenticationProvider는 내부적으로 UserDetailsService를 이용해 사용자 정보를 읽는다.*/
	@Bean
    public DaoAuthenticationProvider authenticationProvider(SecurityService securityService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(securityService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
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
//				.antMatchers("/user/save").permitAll()
//				.antMatchers("/").hasAnyAuthority("ADMIN","USER")
				.anyRequest().permitAll()
				
			.and()
				.csrf().ignoringAntMatchers("/user/save")
			    	
		    .and()
            		.formLogin() // Form 기반의 로그인인 경우
 //           		.loginPage("/login")
		            .defaultSuccessUrl("/loginSuccess") //로그인 성공 후 이동 페이지
		            .failureUrl("/loginFail")// 로그인 실패 후 이동 페이지
//		            .loginProcessingUrl("/login")//로그인을 처리할 url을 설정한다. default값은 "/login" 이다. <form> 태그의 action속성과 맞추어준다.
		       
		            
		     .and()
					.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.deleteCookies("JSESSIONID")
    	
	    	.and()
					.sessionManagement()
					.maximumSessions(1) //같은 아이디로 1명만 로그인
			    	.maxSessionsPreventsLogin(true) //false :신규 로그인은 허용, 기존 사용자는 세션 아웃  true: 이미 로그인한 세션이있으면 로그인 불가 
			    	.expiredUrl("/login"); //세션 아웃되면 이동할 url
//		    	
//			 .and()
//					.exceptionHandling()
//					.accessDeniedPage("/access-denied");
    
       
//    	http.csrf().disable(); // RESTfull을 사용하기 위해서는 csrf 기능을 비활성화해야 함   	
    }
    
    
    // AuthenticationManager: 사용자 인증을 담당
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	 auth.authenticationProvider(authenticationProvider(securityService));
      
    }
  
  // (생략)
}
    


