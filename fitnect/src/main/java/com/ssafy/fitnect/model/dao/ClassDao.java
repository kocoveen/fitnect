package com.ssafy.fitnect.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.fitnect.model.dto.Classes;

public interface ClassDao {
	int insertClassUser(@Param("classId") long classId, @Param("userId") long userId);
	int deleteClassUser(@Param("classId") long classId, @Param("userId") long userId);
	Classes selectClassById(long classId);
	
	List<Classes> selectAllClassByGymId(long gymId);
}
