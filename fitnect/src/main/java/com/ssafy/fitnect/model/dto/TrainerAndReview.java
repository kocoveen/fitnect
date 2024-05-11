package com.ssafy.fitnect.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerAndReview {
	private long trainerId;
	private long userId;
	private long gymId;
	private String career;
	private String major;
	
	private List<ReviewTrainer> reviewTrainer;
}
