package com.nmBoard.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmBoard.test.service.MemberService;
import com.nmBoard.test.vo.Member;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthContoller {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/loginform")
	public String loginform() {
	
		return "main";
	}
	
	  @PostMapping("/login") 
	  public String login(String id, String password) throws Exception {

	    Member member = memberService.get(id, password);

	    if (member == null) {
	        return "main";
	      }
	    
	    return "redirect:/main/list";
	  }

}
