package com.nmBoard.test.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Board {

  private int no;
  private String title;
  private String content;
  private int userNo;

  private User writer;


}

