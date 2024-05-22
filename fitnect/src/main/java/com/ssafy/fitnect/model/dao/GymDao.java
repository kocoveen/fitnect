package com.ssafy.fitnect.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.GymAndTrainerReviewDto;
import com.ssafy.fitnect.model.dto.GymExpiredDto;
import com.ssafy.fitnect.model.dto.SearchCondition;

public interface GymDao {
	public List<Gym> selectAllGym();
	
	public List<Gym> selectAllGymWithAsso();
	public Gym selectOneGymWithAsso(long gymId);
	
	public List<Gym> search(SearchCondition cond);
	public GymAndTrainerReviewDto selectOneWithReview(@Param("gymId") long gymId);

	public int insertRegistUser(
				@Param("userId") long userId,
				@Param("gymId") long gymId,
				@Param("priceId") long priceId, 
				@Param("days") int days
			);
	
	public int deleteRegistUser(
			@Param("userId") long userId,
			@Param("gymId") long gymId
		);

	public int insertFavGym(long gymId, long loginUserId);

	public int deleteFavGym(long gymId, long loginUserId);

	public List<Gym> selectAllFavGym(long userId);
	public List<GymExpiredDto> selectAllMyGym(long userId);

	public Gym selectOneGymByGymId(long gymId);
}
