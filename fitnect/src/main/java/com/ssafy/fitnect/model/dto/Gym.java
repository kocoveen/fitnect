package com.ssafy.fitnect.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gym {
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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
	
	private Amenity amenity; // 1:1
	private List<Classes> classes; // 1:N
	private List<GymMachine> gymMachine; // 1:N
	private List<Prices> prices; // 1:N
}
