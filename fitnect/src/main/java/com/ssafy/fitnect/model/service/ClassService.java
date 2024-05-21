package com.ssafy.fitnect.model.service;

import java.util.List;

import com.ssafy.fitnect.model.dto.Classes;
import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.ReviewTrainer;

public interface ClassService {
	int registClass(long classId, long userId);
	int dropClass(long classId, long userId);
	Classes getClassesById(long classId);
	
	List<Classes> findClassesByGymId(long gymId);
	List<Classes> findAllClassByUserId(long userId);
}
