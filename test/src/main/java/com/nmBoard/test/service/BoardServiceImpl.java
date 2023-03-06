package com.nmBoard.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nmBoard.test.dao.BoardDao;
import com.nmBoard.test.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;

	@Transactional
	@Override
	public void insertBoard(Board board) throws Exception {

		// 게시글 등록
		if (boardDao.insertBoard(board) == 0) {
			throw new Exception("게시글 등록 실패!");
		}

		// 첨부파일 등록
		if (board.getAttachedFiles().size() > 0) {
			boardDao.insertFiles(board);
		}

	}

	@Override
	public List<Board> list() {

		return boardDao.findAll();
	}

	@Override
	public Board getBoardNo(int no) {
		return boardDao.findByNo(no);
	}

	@Transactional
	@Override
	public int updateBoard(Board board) throws Exception {
		
		// 게시글 수정
		if (boardDao.updateBoard(board) == 0) {
			throw new Exception("게시글 수정 실패!");
		}
		// 첨부파일 등록
		if (board.getAttachedFiles().size() > 0) {
			boardDao.insertFiles(board);
		}
		System.out.println("boardDao.insertFiles(board)->>" +  boardDao.insertFiles(board));
		return boardDao.updateBoard(board);
	}

	@Override
	public int delete(int no) {

		return boardDao.delete(no);
	}

	// @Override
	// public AttachedFile getAttachedFile(int fileNo) throws Exception {
	// return boardDao.findFileByNo(fileNo);
	// }

}
