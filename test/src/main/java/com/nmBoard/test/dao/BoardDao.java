package com.nmBoard.test.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.nmBoard.test.vo.AttachedFile;
import com.nmBoard.test.vo.Board;

@Mapper
public interface BoardDao {

  int insertBoard(Board board);

  List<Board> findAll();

  Board findByNo(int no);

  int updateBoard(Board board);

  int deleteBoard(int no);

  int insertFiles(Board board);

  AttachedFile findByAttachedFileNo(int attahedFileNo);

  // 선택 된 첨부파일 삭제
  int deleteAttachedFile(int attahedFileNo);

  // 게시글 삭제 시 첨부 파일 같이 삭제
  int deleteFiles(int no);

}
