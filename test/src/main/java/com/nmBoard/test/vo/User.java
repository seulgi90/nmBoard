package com.nmBoard.test.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class User {
	
	private int userNo;
	private String id;
	private String password;
	private String name;
	
	private String roleName;

}
