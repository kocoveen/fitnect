package com.ssafy.fitnect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewGymUpdateDto {
	private long reviewGymId;
	private long userId;
	private long gymId;
	private String content;
	private int rating = 0;
}
