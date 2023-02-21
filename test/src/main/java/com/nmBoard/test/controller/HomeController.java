package com.nmBoard.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmBoard.test.service.BoardService;
import com.nmBoard.test.vo.Board;

@Controller
@RequestMapping("/main")
public class HomeController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/form")
	public String form() {
		return "form";
	}
	
	@GetMapping("/add")
	public String insert(Board board) {
		
		boardService.insert(board);
		System.out.println("insert 실행 후 data = " + board);
		
		return "redirect:list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
	
		model.addAttribute("boardList", boardService.list());
		
		return "list";
	}
	
	@GetMapping("/detail")
	public ModelAndView detail(int no) {
		
		Board board = boardService.get(no);
		
		return new ModelAndView("detail", "boardD", board);
	}


}
