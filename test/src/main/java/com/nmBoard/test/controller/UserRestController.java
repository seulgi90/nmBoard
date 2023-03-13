package com.nmBoard.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.nmBoard.test.service.UserService;
import com.nmBoard.test.vo.User;

@Controller
public class UserRestController {

  private UserService userService;
  
  public UserRestController(UserService userService) {
	  this.userService = userService;
}
  
  // 회원가입 폼
  @GetMapping("/user/joinform")
  public String joinForm() {
    return "user/joinForm";
  }

  @PostMapping("/user/save")
  public String signUp(User user) {

    user.setRoleName("ROLE_USER");
    userService.joinUser(user);
    
    return "redirect:/login";
  }

}
