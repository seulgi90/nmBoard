package com.nmBoard.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nmBoard.test.service.BoardService;
import com.nmBoard.test.vo.AttachedFile;
import com.nmBoard.test.vo.Board;
import com.nmBoard.test.vo.Criteria;
import com.nmBoard.test.vo.Pagination;
import com.nmBoard.test.vo.UserPrincipal;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	@Autowired
	ServletContext sc;

	@GetMapping("/form")
	public String form() {

		return "board/form";
	}

	@PostMapping("/insertboard")
	public String insertBoard(@AuthenticationPrincipal UserPrincipal userPrincipal, Board board,
			@RequestParam("files") MultipartFile[] files) throws Exception {

		board.setUserNo(userPrincipal.getUserNo());
		board.setAttachedFiles(saveAttachedFiles(files));
		boardService.insertBoard(board);

		return "redirect:boardlist";
	}

//	@GetMapping("/boardlist")
//	public String boardList(Model model) {
//
//		model.addAttribute("boardList", boardService.list());
//
//		return "board/list";
//	}

	@GetMapping("/boardlist")
//	public String boardList(Model model,Criteria cri, @RequestParam(value = "page", defaultValue = "1") int page) {
	public String boardList(Model model, Criteria cri) {

		// 모든 게시글 수 구하기
		Pagination pagination = new Pagination(boardService.getCount(), cri);
		// 계산된 페이지 정보 저장
		cri.setPagination(pagination);

		model.addAttribute("boardList", boardService.getPageList(cri));
//		model.addAttribute("page", page);
		model.addAttribute("pagination", pagination);
		model.addAttribute("cri", cri);

		return "board/list";
	}

	@GetMapping("/detailboard")
	public String detailBoard(int no, Model model) throws Exception {

		model.addAttribute("detailBoard", boardService.getBoardNo(no));

		return "board/detail";
	}

	@PostMapping("/updateboard")
	@ResponseBody
	public Map<String, Object> updateBoard(@AuthenticationPrincipal UserPrincipal userPrincipal, Board board,
			@RequestParam("files") MultipartFile[] files) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		if (userPrincipal.getUserNo() == (board.getUserNo())) {

			board.setAttachedFiles(saveAttachedFiles(files));
			int result = boardService.updateBoard(board);

			map.put("status", result);
			map.put("boardNo", board.getNo());

			return map;
		} else {
			throw new Exception();
		}
	}

	@PostMapping("/deleteboard")
	@ResponseBody
	public Map<String, Object> deleteBoard(@AuthenticationPrincipal UserPrincipal userPrincipal, int no)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		if (boardService.getBoardNo(no).getWriter().getUserNo() == userPrincipal.getUserNo()) {

			int result = boardService.deleteBoard(no);

			map.put("status", result);

			return map;
		} else {
			throw new Exception();
		}
	}

	@GetMapping("/filedelete")
	public String fileDelete(@RequestParam("no") int no, @AuthenticationPrincipal UserPrincipal userPrincipal)
			throws Exception {
		// @RequestParam("no") 값을 받아서 int no로 받는다

		// 첨부 파일 정보 가져오기
		AttachedFile attachedFile = boardService.getAttachedFile(no);
		// 해당 첨부 파일이 있는 게시글 가져오기
		Board board = boardService.getBoardNo(attachedFile.getBoardNo());

		if (board.getUserNo() == userPrincipal.getUserNo()) {

			// 첨부파일을 삭제
			if (boardService.deleteAttachedFile(no) == 0) {
				throw new Exception("게시글 첨부파일 삭제 할 수 없습니다");
			}

		} else {
			throw new Exception("게시글 작성자가 아닙니다");
		}

		return "redirect:detailboard?no=" + board.getNo();

	}

	// 파일 첨부
	// DB에는 파일 관련 정보만 저장하고 실제 파일은 서버 내의 지정 경로에 저장
	private List<AttachedFile> saveAttachedFiles(MultipartFile[] files) throws IOException {

		// 첨부 파일을 가져오기 위한 리스트 생성
		List<AttachedFile> attachedFiles = new ArrayList<>();

		// 프로젝트 디렉터리 내의 저장을 위한 경로 설정
		String dirPath = sc.getRealPath("/board/files");

		for (MultipartFile part : files) {
			if (part.isEmpty()) {
				continue;
			}

			String filename = UUID.randomUUID().toString();

			// FileItem 객체가 가르키는 임시 폴더에 저장된 파일을
			part.transferTo(new File(dirPath + "/" + filename));

			attachedFiles.add(new AttachedFile(filename));
		}

		return attachedFiles;
	}

}
