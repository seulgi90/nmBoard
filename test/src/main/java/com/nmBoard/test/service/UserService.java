package com.nmBoard.test.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nmBoard.test.dao.UserDao;
import com.nmBoard.test.vo.User;
import com.nmBoard.test.vo.UserPrincipal;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		// DB로부터 회원 정보를 가져온다
		ArrayList<User> userAuthes = userDao.findByUserId(id);

		if (userAuthes.size() == 0) {
			throw new UsernameNotFoundException("User " + id + " Not Found!");
		}

		// UserDetails 클래스를 상속받은 UserPrincipal 리턴
		return new UserPrincipal(userAuthes);
	}

	// 회원가입도중 오류가 날수 있으므로 transactional을 사용
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public String joinUser(User user) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		int flag = userDao.userSave(user);
		if (flag > 0) {

			int userNo = userDao.findUserNo(user.getId());
			int roleNo = userDao.findRoleNo(user.getRoleName());

			userDao.userRoleSave(userNo, roleNo);
			return "success";
		}
		return "fail";
	}
}
