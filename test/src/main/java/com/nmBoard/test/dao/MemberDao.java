package com.nmBoard.test.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nmBoard.test.vo.Member;

@Mapper
public interface MemberDao {
	
	Member findByIdPassword(@Param("id") String id,  @Param("password") String password);


}
