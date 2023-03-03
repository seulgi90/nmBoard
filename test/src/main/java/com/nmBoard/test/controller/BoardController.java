package com.nmBoard.test.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
  public String detailBoard(int no, Model model) throws Exception {
	
    model.addAttribute("detailBoard", boardService.getBoardNo(no));
    System.out.println("model -> " + model);
    return "board/detail";
  }

  @PostMapping("/updateboard")
  public String updateBoard(@AuthenticationPrincipal UserPrincipal userPrincipal, Board board) throws Exception{
	
    if(userPrincipal.getUserNo() == (board.getUserNo())) {
    	
    	boardService.updateBoard(board);
    	
    	return "redirect:/board/boardlist";
    	
    } else {
    	throw new Exception("게시글 작성자가 아닙니다");
    }
	
  }

  @PostMapping("/deleteboard")
  public String deleteBoard(int no) {
	
    boardService.delete(no);
    
    return "redirect:/board/boardlist";

  }

}
