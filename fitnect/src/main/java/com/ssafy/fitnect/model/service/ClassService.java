package com.ssafy.fitnect.model.service;

import java.util.List;

import com.ssafy.fitnect.model.dto.Classes;

public interface ClassService {
	int registClass(long classId, long userId);
	int dropClass(long classId, long userId);
	Classes getClassesById(long classId);
	
	List<Classes> findClassesByGymId(long gymId);
}
