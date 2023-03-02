package com.nmBoard.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmBoard.test.dao.BoardDao;
import com.nmBoard.test.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public Map<String, Object> insertBoard(Board board) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = boardDao.insertBoard(board);
		
		if(result > 0) {
			
			map.put("status", result);
		}
		
		return	map;
	}

	@Override
	public List<Board> list() {
		
		return boardDao.findAll();
	}

	@Override
	public Board getBoardNo(int no) {
		return boardDao.findByNo(no);
	}

	@Override
	public int updateBoard(Board board) {
		
		return boardDao.updateBoard(board);
	}

	@Override
	public int delete(int no) {
		
		return boardDao.delete(no);
	}
	
	
	
}
