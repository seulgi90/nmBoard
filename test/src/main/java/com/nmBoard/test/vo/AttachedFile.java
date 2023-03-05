package com.nmBoard.test.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AttachedFile {

  private int attachedFileNo;
  private String filepath;

  private int boardNo;

  public AttachedFile() {
  }

  public AttachedFile(String filepath) {
    this.filepath = filepath;
  }

}
