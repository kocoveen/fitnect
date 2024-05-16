package com.ssafy.fitnect.model.service;

import java.util.List;

import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.GymAndTrainerReviewDto;
import com.ssafy.fitnect.model.dto.SearchCondition;

public interface GymService {
	
	public List<Gym> getAllGym();
	public List<Gym> searchGym(SearchCondition cond);

	
	public List<Gym> getAllGymWithAsso();
	public Gym getOneGymWithAsso(long gymId);

	public GymAndTrainerReviewDto gymGetOneByIdWithReview(long id);
	
	public int registGym(long userId, long gymId, long priceId);
	public int quitGym(long userId, long gymId);
	
	public int favGym(long gymId, long loginUserId);
	public int unfavGym(long gymId, long loginUserId);
	
	
	public List<Gym> findFavGymByUserId(long userId);
	public List<Gym> findMyGymByUserId(long userId);

}
