package com.ssafy.fitnect.model.service;

import java.util.List;

import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.UserSignUpRequestDto;
import com.ssafy.fitnect.model.dto.Users;

public interface UserService {

	public Users getUserById(long id);
	public int insert(UserSignUpRequestDto user);
	public int update(Users user);
	
	public int delete(int id);
	public Users getUserByEmail(String email);


}
