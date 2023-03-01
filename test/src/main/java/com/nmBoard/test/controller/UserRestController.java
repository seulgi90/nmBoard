package com.nmBoard.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.nmBoard.test.service.UserService;

public class UserRestController {


  @Autowired
  private UserService userService;

  //	@PostMapping("/user/save")
  //	public String saveUserInfo(@RequestBody User user) {
  //		
  //		return userService.joinUser(user);
  //	}

}
