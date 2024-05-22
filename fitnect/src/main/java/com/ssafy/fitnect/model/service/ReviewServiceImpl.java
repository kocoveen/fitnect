package com.ssafy.fitnect.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitnect.model.dao.ReviewDao;
import com.ssafy.fitnect.model.dto.ReviewGym;
import com.ssafy.fitnect.model.dto.ReviewGymSaveDto;
import com.ssafy.fitnect.model.dto.ReviewGymUpdateDto;
import com.ssafy.fitnect.model.dto.ReviewTrainer;
import com.ssafy.fitnect.model.dto.ReviewTrainerSaveDto;
import com.ssafy.fitnect.model.dto.ReviewTrainerUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
	
	private final ReviewDao dao;

	@Override
	public List<ReviewGym> findAllReviewGymById(long gymId) {
		return dao.selectAllReviewGymById(gymId);
	}
	
	@Override
	public ReviewGym findOneReviewGymById(long id) {
		return dao.selectOneReviewGymById(id);
	}
	
	@Override
	public int writeReviewGym(ReviewGymSaveDto review) {
		return dao.insertReviewGym(review);
	}

	@Override
	public int modifyReviewGym(ReviewGymUpdateDto review) {
		return dao.updateReviewGym(review);
	}
	
	@Override
	public int removeReviewGym(long id) {
		return dao.deleteReviewGym(id);
	}
	
	
	
	
	@Override
	public List<ReviewTrainer> findAllReviewTrainerById(long trainerId) {
		return dao.selectAllReviewTrainerById(trainerId);
	}
	
	@Override
	public ReviewTrainer findOneReviewTrainerById(long id) {
		// TODO Auto-generated method stub
		return dao.selectOneReviewTrainerById(id);
	}

	@Override
	public int writeReviewTrainer(ReviewTrainerSaveDto reviewTrainer) {
		return dao.insertReviewTrainer(reviewTrainer);
	}

	@Override
	public int modifyReviewTrainer(ReviewTrainerUpdateDto reviewTrainer) {
		return dao.updateReviewTrainer(reviewTrainer);
	}

	@Override
	public int removeReviewTrainer(long id) {
		return dao.deleteReviewTrainer(id);
	}

	@Override
	public List<ReviewGym> findAllGymReviewByUserId(long loginUserId) {
		return dao.selectAllReviewGymByUserId(loginUserId);
	}

	@Override
	public List<ReviewTrainer> findAllTrainerReviewByUserId(long loginUserId) {
		return dao.selectAllReviewTrainerByUserId(loginUserId);
	}
	
	
	

}
