package com.ssafy.fitnect.model.dao;

import java.util.List;

import com.ssafy.fitnect.payment.TotalInfoDto;

public interface OrderDao {
	
//	TotalInfoDto selectOrderOne(long orderId);

	List<TotalInfoDto> selectOrderAll();
	int insertOrder(TotalInfoDto totalInfoDto);
}
