package com.ssafy.fitnect.payment;

import java.util.List;

public interface OrderService {
	
//	TotalInfoDto getOrderOne();
	List<TotalInfoDto> getOrderAll();
	
	int addOrder(TotalInfoDto totalInfoDto);
}
