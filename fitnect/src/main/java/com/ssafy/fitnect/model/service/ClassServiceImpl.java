package com.ssafy.fitnect.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitnect.model.dao.ClassDao;
import com.ssafy.fitnect.model.dto.Classes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

	private final ClassDao classDao; 
	
	@Override
	public int registClass(long classId, long userId) {
		return classDao.insertClassUser(classId, userId);
	}

	@Override
	public int dropClass(long classId, long userId) {
		return classDao.deleteClassUser(classId, userId);
	}

	@Override
	public Classes getClassesById(long classId) {
		return classDao.selectClassById(classId);
	}

	@Override
	public List<Classes> findClassesByGymId(long gymId) {
		return classDao.selectAllClassByGymId(gymId);
	}
	
}
