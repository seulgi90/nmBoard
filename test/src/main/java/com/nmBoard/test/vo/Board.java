package com.nmBoard.test.vo;

public class Board {

	private int no;
	private String title;
	private String content;
	private int userNo;
	
	private User writer;
	
	
	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}


	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}

	

	public int getUserNo() {
		return userNo;
	}



	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}



	public User getWriter() {
		return writer;
	}



	public void setWriter(User writer) {
		this.writer = writer;
	}



	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", content=" + content + ", userNo=" + userNo + ", writer="
				+ writer + "]";
	}



	
	
}
