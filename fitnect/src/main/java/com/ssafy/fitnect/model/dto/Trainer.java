package com.ssafy.fitnect.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
	
	private long trainorId;
	private long userId;
	private long gymId;
	private String career;
	private String major;
	
}
