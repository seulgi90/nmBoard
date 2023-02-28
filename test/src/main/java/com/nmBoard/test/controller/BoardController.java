package com.nmBoard.test.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nmBoard.test.service.BoardService;
import com.nmBoard.test.vo.Board;
import com.nmBoard.test.vo.User;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/form")
	public String form() {
		
		return "board/form";
	}
	
	@GetMapping("/insertboard")
	public String insertBoard(@AuthenticationPrincipal User user, Board board) {

board.setWriter(user);
		boardService.insertBoard(board);
		System.out.println("data chec ->" + board);
		
		
		return "redirect:list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
	
		model.addAttribute("boardList", boardService.list());
		
		return "board/list";
	}
	
	@GetMapping("/detail")
	public ModelAndView detail(int no) {
		
		Board board = boardService.get(no);
		
		return new ModelAndView("board/detail", "detailBoard", board);
	}

	@PostMapping("/update")
	@ResponseBody
	public void update(Board board) {
		
		boardService.update(board);
		
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public void delete(int no) {
		
		boardService.delete(no);
		
	}
	

}
