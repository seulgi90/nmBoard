package com.nmBoard.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.nmBoard.test.service.UserService;
import com.nmBoard.test.vo.User;

@Controller
public class UserRestController {


  @Autowired
  private UserService userService;

  // 회원가입 폼
  @GetMapping("/user/joinform")
  public String joinForm() {
    return "user/joinForm";
  }

  @PostMapping("/user/save")
  public String signUp(User user) {

    System.out.println("user save->>" + user);
    user.setRoleName("ROLE_USER");
    userService.joinUser(user);
    System.out.println("user save result->>" + user);
    return "redirect:/login";
  }

  //  	@PostMapping("/user/save")
  //  	public String saveUserInfo(@RequestBody User user) {
  //  		
  //  		return userService.joinUser(user);
  //  	}

}
