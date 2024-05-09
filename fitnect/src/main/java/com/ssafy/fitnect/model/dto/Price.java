package com.ssafy.fitnect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {
	private long priceId;
	private long gymId;
	private String name;
	private int price;
	private String text;
	
}
