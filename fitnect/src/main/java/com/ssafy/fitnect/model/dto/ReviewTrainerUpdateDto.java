package com.ssafy.fitnect.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewTrainerUpdateDto {
	private long reviewTrainerId;
	private long userId;
	private long trainerId;
	private String content;
	private int trainerRating;
}
