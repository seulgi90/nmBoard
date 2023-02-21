package com.nmBoard.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nmBoard.test.dao.BoardDao;
import com.nmBoard.test.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public void insert(Board board) {
		boardDao.insert(board);
	}

	@Override
	public List<Board> list() {
		
		return boardDao.findAll();
	}

	@Override
	public Board get(int no) {
		return boardDao.findByNo(no);
	}
	
	
	
}
