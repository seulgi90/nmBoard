package com.nmBoard.test.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.nmBoard.test.vo.UserPrincipal;


@Controller
public class LoginController {

  // 메인 페이지
  @GetMapping("/main")
  public String index() {
    return "index";
  }

//  @GetMapping("/login")
//  public String loadExceptionPage(ModelMap model) throws Exception{
//
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
//
//    model.addAttribute("name",userPrincipal.getUsername());
//    model.addAttribute("auth",userPrincipal.getAuthorities());
//    
//    System.out.println("userPrincipal.getUsername()" + userPrincipal.getUsername());
//    
//    return "login";
//  }


  @GetMapping("/user/loginSuccess")
  public void loginSuccess() {
  }

  // 로그아웃 결과 페이지
  @GetMapping("/logout")
  public String logout() {
    return "/logout";
  }
}
