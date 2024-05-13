package com.ssafy.fitnect.model.dao;

import com.ssafy.fitnect.model.dto.PriceDayDto;

public interface PriceDao {
	PriceDayDto selectPriceOneByPriceId(long id);
}
