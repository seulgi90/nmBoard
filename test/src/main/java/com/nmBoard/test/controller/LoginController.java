package com.nmBoard.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public void index() {

	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
	}
	
	@GetMapping("/loginFail")
	public void loginFail() {
	}
}
