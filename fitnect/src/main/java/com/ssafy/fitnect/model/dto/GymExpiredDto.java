package com.ssafy.fitnect.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GymExpiredDto {
	private final String gymId;
	private final String name;
	private final String address;
	private final String phone;
	private final String content;
	private final String type;
	private final String operationHours;
	private final String closedDay;
	private final int capacity;
	private final double rating;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime expiredDate;
}
