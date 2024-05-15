package com.ssafy.fitnect.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.fitnect.auth.CustomUserDetails;
import com.ssafy.fitnect.model.dao.UserDao;
import com.ssafy.fitnect.model.dto.UserSignUpRequestDto;
import com.ssafy.fitnect.model.dto.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDao dao;
	
	@Override
	public Users getUserById(long id) {
		return dao.selectOne(id);
	}

	@Override
	public int insert(UserSignUpRequestDto user) {
		return dao.insertUser(user);
	}

	@Override
	public int update(Users user) {
		return dao.updateUser(user);
	}

	@Override
	public int delete(int id) {
		return dao.deleteUser(id);
	}

	@Override
	public Users getUserByEmail(String email) {
		return dao.getUserByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = dao.getUserByEmail(email);
		if (user != null) {
//			return new CustomUserDetails(
//						user.getUserId(), 
//						user.getEmail(), 
//						user.getPassword(), 
//						user.getName(),
//						user.getLatitude(),
//						user.getLongitude()
//					);
			return CustomUserDetails.builder()
					.userId(user.getUserId())
					.email(user.getEmail())
					.password(user.getPassword())
					.name(user.getName())
					.latitude(user.getLatitude())
					.longitude(user.getLongitude())
					.build();
		} else {
			throw new UsernameNotFoundException("user not found:" + email);
		}
	}
	

}
