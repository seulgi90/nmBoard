package com.nmBoard.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nmBoard.test.service.SecurityService;
import com.nmBoard.test.vo.User;

public class UserRestController {
	

	@Autowired
	private SecurityService securityService;
	
	@PostMapping("/user/save")
	public String saveUserInfo(@RequestBody User user) {
		return securityService.InsertUser(user);
	}

}
