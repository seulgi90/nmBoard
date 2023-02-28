package com.nmBoard.test.service;

import java.util.List;

import com.nmBoard.test.vo.Board;

public interface BoardService {

	   int insertBoard(Board board);
	   
	   List<Board> list();

	   Board get(int no);

	   int update(Board board);

	   int delete(int no);

}
