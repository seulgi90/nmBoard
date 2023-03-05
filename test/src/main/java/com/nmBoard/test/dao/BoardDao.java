package com.nmBoard.test.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.nmBoard.test.vo.Board;

@Mapper
public interface BoardDao {

  int insertBoard(Board board);

  List<Board> findAll();

  Board findByNo(int no);

  int updateBoard(Board board);

  int delete(int no);

  int insertFiles(Board board);

  //  AttachedFile findFileByNo(int fileNo);

}
