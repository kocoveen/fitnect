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
public class GymAndTrainerReviewDto {
	private long gymId;
	private String name;
	private String address;
	private double longitude;
	private double latitude;
	private String phone;
	private String content;
	private String type;
	private String operationHours;
	private String closedDay;
	private String gymImgUrl;
	private int capacity;
	private double rating = 0d;
	
	private List<ReviewGym> reviewGym;
	
	private List<TrainerAndReview> trainerAndReview;
}
