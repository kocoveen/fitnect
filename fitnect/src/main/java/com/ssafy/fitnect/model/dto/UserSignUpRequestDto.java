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
public class UserSignUpRequestDto {
	private long userId;
	private String email;
	private String password;
	private String phone;
	private String name;
	private String address;
	private double longitude;
	private double latitude;
	private String auth;
}
