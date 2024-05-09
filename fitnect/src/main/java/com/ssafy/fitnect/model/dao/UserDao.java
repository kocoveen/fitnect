package com.ssafy.fitnect.model.dao;

import com.ssafy.fitnect.model.dto.Users;

public interface UserDao {
	
    Users selectOne(int id);
    int insert(Users user);
    int update(Users user);
    int delete(int id);
	Users getUserByEamil(String email);

}
