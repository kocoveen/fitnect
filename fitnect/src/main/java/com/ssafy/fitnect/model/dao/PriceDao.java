package com.ssafy.fitnect.model.dao;

import com.ssafy.fitnect.model.dto.Prices;

public interface PriceDao {
	Prices selectPriceOneByPriceId(long id);
}
