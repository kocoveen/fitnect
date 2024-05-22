package com.ssafy.fitnect.payment;

import org.springframework.stereotype.Service;

import com.ssafy.fitnect.model.dao.ClassDao;
import com.ssafy.fitnect.model.dao.PriceDao;
import com.ssafy.fitnect.model.dao.RegistClassDao;
import com.ssafy.fitnect.model.dao.RegistGymDao;
import com.ssafy.fitnect.model.dto.Classes;
import com.ssafy.fitnect.model.dto.Prices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistServiceImpl implements RegistService {
	
	
	private final PriceDao priceDao;
	private final ClassDao classDao;
	private final RegistGymDao registGymDao;
	private final RegistClassDao registClassDao;

	@Override
	public Prices findGymPriceById(long priceId) {
		return priceDao.selectPriceOneByPriceId(priceId);
	}

	@Override
	public int registGym(RegistGymDto registGymDto) {
		return registGymDao.insertRegistUserOne(registGymDto);
	}
	
	
	

	@Override
	public Classes findClassPriceById(long classId) {
		return classDao.selectClassById(classId);
	}

	@Override
	public int registClass(RegistClassDto registClassDto) {
		return registClassDao.insertRegistClassOne(registClassDto);
	}

}
