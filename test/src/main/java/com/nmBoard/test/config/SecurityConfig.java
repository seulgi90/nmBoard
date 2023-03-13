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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.nmBoard.test.service.UserServiceImpl;

@Configuration
@EnableWebSecurity //웹보안 활성화를위한 annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private UserServiceImpl userService;

  public SecurityConfig(UserServiceImpl userService) {
	  this.userService = userService;
}

  // 비밀번호 암호화
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // DaoAuthenticationProvider는 내부적으로 UserDetailsService를 이용해 사용자 정보를 읽는다
  @Bean
  public DaoAuthenticationProvider authenticationProvider(UserServiceImpl userService) {
	  
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    
    return authenticationProvider;
  }

  // WebSecurity는 FilterChainProxy를 생성하는 필터
  // 위 설정을 통해 Spring Security에서 해당 요청은 인증 대상에서 제외
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
  }

  // antMatchers: 페이지에 접근할 수 있는 권한을 설정
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
    .antMatchers("/user/save").permitAll()
    .antMatchers("/").hasAnyAuthority("ROLE_ADMIN","ROLE_USER") // hasAnyAuthority: 사용자가 주어진 권한 중 어떤 것이라도 있으면 접근 허용
    .anyRequest().permitAll() // 모든 요청은 인증된 사용자만 접근 가능

    .and()
    .formLogin() // Form 기반의 로그인인 경우
    .usernameParameter("id")
    .defaultSuccessUrl("/user/loginSuccess") //로그인 성공 후 이동 페이지

    .and()
    .logout()
    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    .invalidateHttpSession(true) // 로그아웃 이후 세션 전체 삭제 여부
    .deleteCookies("JSESSIONID");

//    http.csrf().disable();

  }


  // AuthenticationManager: 사용자 인증을 담당
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider(userService));

  }
}



