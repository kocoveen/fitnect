package com.ssafy.fitnect.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fitnect.model.dao.GymDao;
import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.SearchCondition;

@Service
public class GymServiceImpl implements GymService {
	
	private final GymDao gymDao;
	
	@Autowired
	public GymServiceImpl(GymDao gymDao) {
		this.gymDao = gymDao;
	}

	@Override
	public List<Gym> searchGym(SearchCondition cond) {
		return null;
	} 

	@Override
	public List<Gym> getAllGym() {
		return gymDao.selectAllGym();
	}
	
	@Override
	public List<Gym> getAllGymWithAsso() {
		return gymDao.selectAllGymWithAsso();
	}
	

}
