package com.ssafy.fitnect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amenity {
	private long amenityId;
	private long gymId;
	private boolean freeParking;
	private boolean paidParking;
	private boolean allDay;
	private boolean showerFacilities;
	private boolean sportsWear;
	private boolean sharedLocker;
	private boolean personalLocker;
	private boolean unmanded;
	
}
