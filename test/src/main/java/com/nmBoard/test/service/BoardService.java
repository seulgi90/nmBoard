package com.nmBoard.test.service;

import java.util.List;

import com.nmBoard.test.vo.AttachedFile;
import com.nmBoard.test.vo.Board;

public interface BoardService {

  void insertBoard(Board board) throws Exception;

  List<Board> list();

  Board getBoardNo(int no);

  int updateBoard(Board board) throws Exception;

  int deleteBoard(int no);

  AttachedFile getAttachedFile(int attahedFileNo) throws Exception;

  int deleteAttachedFile(int attahedFileNo) throws Exception;

}
