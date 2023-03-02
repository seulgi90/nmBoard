package com.nmBoard.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.nmBoard.test.vo.UserPrincipal;

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
	  @ResponseBody
	  public Map<String, Object> insertBoard(@AuthenticationPrincipal UserPrincipal userPrincipal,Board board) {
		
	    board.setUserNo(userPrincipal.getUserNo());
	    
	    return  boardService.insertBoard(board);
	    		

	  }

	@GetMapping("/boardlist")
	public String boardList(Model model) {

		model.addAttribute("boardList", boardService.list());

		return "board/list";
	}

	@GetMapping("/detail")
	public String detailBoard(@AuthenticationPrincipal UserPrincipal userPrincipal, int no, Model model) throws Exception {

		Board board = boardService.getBoardNo(no);
		
		model.addAttribute("detailBoard", board);
//		if(userPrincipal.getUserNo() == (board.getWriter().getUserNo())) {
//			
//		}

		return "board/detail";
	}

	@PostMapping("/updateboard")
	@ResponseBody
	public void updateBoard(Board board) {

		boardService.updateBoard(board);
		System.out.println("up board ==>" + board);
	}

	@PostMapping("/delete")
	@ResponseBody
	public void delete(int no) {

		boardService.delete(no);

	}

}
