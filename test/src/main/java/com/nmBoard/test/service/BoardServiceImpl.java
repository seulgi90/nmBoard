package com.nmBoard.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nmBoard.test.dao.BoardDao;
import com.nmBoard.test.vo.AttachedFile;
import com.nmBoard.test.vo.Board;
import com.nmBoard.test.vo.Criteria;
import com.nmBoard.test.vo.Pagination;

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
	
//	@Override
//	public List<Board> list() {
//
//		return boardDao.findAll();
//	}

	@Override
	public List<Board> getPageList(Criteria cri) {

		return boardDao.getPageList(cri);
	}
	
	@Override
	public int getCount() {
		return boardDao.getCount();
	}

	@Override
	public Board getBoardNo(int no) {
		return boardDao.findByNo(no);
	}

	@Transactional
	@Override
	public int updateBoard(Board board) throws Exception {
		
//		// 게시글 수정
//		if (boardDao.updateBoard(board) == 0) {
//			throw new Exception("게시글 수정 실패!");
//		}
		// 첨부파일 등록
		if (board.getAttachedFiles().size() > 0) {
			boardDao.insertFiles(board);
		}
		
		return boardDao.updateBoard(board);
	}
	
	@Transactional
	@Override
	public int deleteBoard(int no) {
		
		boardDao.deleteFiles(no);
		
		return boardDao.deleteBoard(no);
	}

	@Override
	public AttachedFile getAttachedFile(int attahedFileNo) throws Exception {
		
		return boardDao.findByAttachedFileNo(attahedFileNo);
	}
	
	@Override
	public int deleteAttachedFile(int attahedFileNo) throws Exception {
		
		return boardDao.deleteAttachedFile(attahedFileNo);
	}
	
	@Transactional
	@Override
	public List<Board> searchKeyword(String keyword) {
		
		return boardDao.findByKeyword(keyword);
	}
	
}
