package com.nmBoard.test.service;

import java.util.List;
import com.nmBoard.test.vo.Board;

public interface BoardService {

  void insertBoard(Board board) throws Exception;

  List<Board> list();

  Board getBoardNo(int no);

  int updateBoard(Board board);

  int delete(int no);

  //  AttachedFile getAttachedFile(int fileNo) throws Exception;


}
