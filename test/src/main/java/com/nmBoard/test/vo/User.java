package com.nmBoard.test.vo;

public class User {
	
	private int userNo;
	private String id;
	private String password;
	private String name;
	
	private String roleName;
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", id=" + id + ", password=" + password + ", name=" + name + ", roleName=" + roleName + "]";
	}
	
	
	

}
