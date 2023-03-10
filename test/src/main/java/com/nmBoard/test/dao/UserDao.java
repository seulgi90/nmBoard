package com.nmBoard.test.dao;


import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.nmBoard.test.vo.User;

@Mapper
public interface UserDao {

  //유저 정보
  ArrayList<User> findByUserId(@Param("id") String id);

  //유저 저장
  int userSave(User user);

  //유저 권한 저장
  int userRoleSave(@Param("userNo") int userNo, @Param("roleNo") int roleNo);

  //유저 FK번호 알아내기
  int findUserNo(@Param("id") String id);

  //권한 FK번호 알아내기
  int findRoleNo(@Param("roleName") String roleName);




}
