package com.nmBoard.test.service;

import java.util.List;

import com.nmBoard.test.vo.AttachedFile;
import com.nmBoard.test.vo.Board;
import com.nmBoard.test.vo.Criteria;

public interface BoardService {

  void insertBoard(Board board) throws Exception;

  // 전체 게시글 + 페이징
  List<Board> getPageList(Criteria cri);

  Board getBoardNo(int no);

  int updateBoard(Board board) throws Exception;

  int deleteBoard(int no);

  AttachedFile getAttachedFile(int attahedFileNo) throws Exception;

  int deleteAttachedFile(int attahedFileNo) throws Exception;
  
  /** 페이징을 위한 전체 데이터 개수 파악 */
  int getCountBoard(); 
  

}
