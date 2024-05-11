package com.ssafy.fitnect.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitnect.model.dto.ReviewGym;
import com.ssafy.fitnect.model.dto.ReviewGymSaveDto;
import com.ssafy.fitnect.model.dto.ReviewGymUpdateDto;
import com.ssafy.fitnect.model.dto.ReviewTrainer;
import com.ssafy.fitnect.model.dto.ReviewTrainerSaveDto;
import com.ssafy.fitnect.model.dto.ReviewTrainerUpdateDto;

import lombok.NoArgsConstructor;

@Service
public interface ReviewService {
	
	public List<ReviewGym> findAllReviewGymById(long gymId);
	public ReviewGym findOneReviewGymById(long id);
	public int writeReviewGym(ReviewGymSaveDto review);
	public int modifyReviewGym(ReviewGymUpdateDto review);
	public int removeReviewGym(long id);
	
	public List<ReviewTrainer> findAllReviewTrainerById(long trainerId);
	public ReviewTrainer findOneReviewTrainerById(long id);
	public int writeReviewTrainer(ReviewTrainerSaveDto reviewTrainer);
	public int modifyReviewTrainer(ReviewTrainerUpdateDto reviewTrainer);
	public int removeReviewTrainer(long id);
	
	
}
