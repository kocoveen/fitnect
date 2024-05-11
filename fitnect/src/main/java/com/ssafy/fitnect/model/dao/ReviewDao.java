package com.ssafy.fitnect.model.dao;

import java.util.List;

import com.ssafy.fitnect.model.dto.GymRatingDto;
import com.ssafy.fitnect.model.dto.ReviewGym;
import com.ssafy.fitnect.model.dto.ReviewGymSaveDto;
import com.ssafy.fitnect.model.dto.ReviewGymUpdateDto;
import com.ssafy.fitnect.model.dto.ReviewTrainer;
import com.ssafy.fitnect.model.dto.ReviewTrainerSaveDto;
import com.ssafy.fitnect.model.dto.ReviewTrainerUpdateDto;

public interface ReviewDao {
	
	public List<ReviewGym> selectAllReviewGymById(long gymId);
	public ReviewGym selectOneReviewGymById(long id);
	public int insertReviewGym(ReviewGymSaveDto review);
	public int updateReviewGym(ReviewGymUpdateDto review);
	public int deleteReviewGym(long id);
	
	public GymRatingDto selectAvgGymRating(long id);
	public int gymRating(GymRatingDto gymRatingDto);
	
	
	public List<ReviewTrainer> selectAllReviewTrainerById(long trainerId);
	public ReviewTrainer selectOneReviewTrainerById(long id);
	public int insertReviewTrainer(ReviewTrainerSaveDto reviewTrainer);
	public int updateReviewTrainer(ReviewTrainerUpdateDto reviewTrainer);
	public int deleteReviewTrainer(long id);


}
