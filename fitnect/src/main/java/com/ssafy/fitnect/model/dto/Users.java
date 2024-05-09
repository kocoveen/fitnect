package com.ssafy.fitnect.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	
	private int userId;
	private String email;
	private String password;
	private String phone;
	private String name;
	private String address;
	private double longitude;
	private double latitude;
	private LocalDateTime createDate;
	private LocalDateTime modifiedDate;
	private String profileImgUrl;
	private int height;
	private int weight;
	private String auth;
	
	
}
