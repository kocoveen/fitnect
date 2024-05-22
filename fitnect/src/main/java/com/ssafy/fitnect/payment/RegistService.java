package com.ssafy.fitnect.payment;

import com.ssafy.fitnect.model.dto.Classes;
import com.ssafy.fitnect.model.dto.Prices;

public interface RegistService {
	Prices findGymPriceById(long priceId);
	Classes findClassPriceById(long classId);
	
	int registGym(RegistGymDto registGymDto);
	int registClass(RegistClassDto registClassDto);
}
