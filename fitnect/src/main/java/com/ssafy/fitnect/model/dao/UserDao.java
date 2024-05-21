package com.ssafy.fitnect.model.dao;

import com.ssafy.fitnect.model.dto.UserEmailNameDto;
import com.ssafy.fitnect.model.dto.UserSignUpRequestDto;
import com.ssafy.fitnect.model.dto.Users;
import com.ssafy.fitnect.model.dto.UsersUpdatePasswordDto;

public interface UserDao {
	
    Users selectOne(long id);
    int insertUser(UserSignUpRequestDto user);
    int updateUser(Users user);
    int deleteUser(int id);
	Users getUserByEmail(String email);
	
	int updatePassword(UsersUpdatePasswordDto userIdAndPassword);
	
	int selectUserEmailAndName(UserEmailNameDto userEmailNameDto);
}
