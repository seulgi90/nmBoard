package com.nmBoard.test.vo;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

  // UID값을 명시 해주지 않으면 자바 컴파일러가 임시적인 값을 부여한다.
  private static final long serialVersionUID = 1L;

  private ArrayList<User> user;	


  public UserPrincipal(ArrayList<User> userAuthes) {
    this.user = userAuthes;
  }


  // 유저가 갖고 있는 권한 목록
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    for(int x=0; x < user.size(); x++) {
      authorities.add(new SimpleGrantedAuthority(user.get(x).getRoleName()));
    }

    return authorities;
  }


  @Override
  public String getPassword() {
    return user.get(0).getPassword();
  }

  @Override
  public String getUsername() {
    return user.get(0).getName();
  }

  // vo의 userNo
  public int getUserNo() {
    return user.get(0).getUserNo();
  }



  // 유저 아이디가 만료되었는지
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  // 비밀번호가 만료 되었는지
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  // 계정이 활성화 되어있는지
  @Override
  public boolean isEnabled() {
    return true;
  }

}
