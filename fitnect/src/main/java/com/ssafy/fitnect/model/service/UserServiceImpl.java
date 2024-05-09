package com.ssafy.fitnect.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fitnect.model.dao.UserDao;
import com.ssafy.fitnect.model.dto.Users;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public Users getUserById(int id) {
		return dao.selectOne(id);
	}

	@Override
	public int insert(Users user) {
		return dao.insert(user);	
	}

	@Override
	public int update(Users user) {
		return dao.update(user);
	}

	@Override
	public int delete(int id) {
		return dao.delete(id);
	}

	@Override
	public Users getUserByEmail(String email) {
		return dao.getUserByEamil(email);
	}
	

}
