package com.nmBoard.test.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.nmBoard.test.vo.UserPrincipal;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String loadExceptionPage(ModelMap model) throws Exception{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		
		model.addAttribute("name",userPrincipal.getUsername());
		model.addAttribute("auth",userPrincipal.getAuthorities());
		
		return "login";
	}
	
	// 접근 금지 페이지
//	@GetMapping("/access-denied")
//	public String loadAccessdeniedPage() throws Exception{
//		return "index";
//	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
	}
	
	@GetMapping("/loginFail")
	public void loginFail() {
	}
}
