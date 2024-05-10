package com.ssafy.fitnect.model.service;

import java.util.List;

import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.SearchCondition;

public interface GymService {
	
	public List<Gym> getAllGym();
	
	
	public List<Gym> searchGym(SearchCondition cond);

	public List<Gym> getAllGymWithAsso(); 
}
