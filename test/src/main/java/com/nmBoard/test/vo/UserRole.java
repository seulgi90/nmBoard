package com.nmBoard.test.vo;

public class UserRole {
	
	private int user_no;
	private int role_no;
	
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getRole_no() {
		return role_no;
	}
	public void setRole_no(int role_no) {
		this.role_no = role_no;
	}
	
	@Override
	public String toString() {
		return "UserRole [user_no=" + user_no + ", role_no=" + role_no + "]";
	}
	
	

	
}
