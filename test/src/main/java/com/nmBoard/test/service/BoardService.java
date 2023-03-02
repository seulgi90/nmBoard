package com.nmBoard.test.service;

import java.util.List;
import java.util.Map;

import com.nmBoard.test.vo.Board;

public interface BoardService {

	Map<String, Object> insertBoard(Board board);
	    
	    List<Board> list();

	    Board getBoardNo(int no);

	    int updateBoard(Board board);

	    int delete(int no);

}
