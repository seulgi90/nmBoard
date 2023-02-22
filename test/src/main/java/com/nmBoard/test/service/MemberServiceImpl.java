package com.nmBoard.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmBoard.test.dao.MemberDao;
import com.nmBoard.test.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao memberDao;

	@Override
	public Member get(String id, String password) {
		return memberDao.findByIdPassword(id,password);
	}


	
	
	
}
