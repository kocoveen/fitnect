package com.ssafy.fitnect.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fitnect.model.dao.GymDao;
import com.ssafy.fitnect.model.dao.PriceDao;
import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.GymAndTrainerReviewDto;
import com.ssafy.fitnect.model.dto.GymExpiredDto;
import com.ssafy.fitnect.model.dto.PriceDayDto;
import com.ssafy.fitnect.model.dto.SearchCondition;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GymServiceImpl implements GymService {
	
	private final GymDao gymDao;
	private final PriceDao priceDao;
	
	@Autowired
	public GymServiceImpl(GymDao gymDao, PriceDao priceDao) {
		this.gymDao = gymDao;
		this.priceDao = priceDao;
	}
	

	@Override
	public Gym findGymOneById(long gymId) {
		// TODO Auto-generated method stub
		return gymDao.selectOneGymByGymId(gymId);
	}

	@Override
	public List<Gym> searchGym(SearchCondition cond) {
		return null;
	}
	
	@Override
	public Gym getOneGymWithAsso(long gymId) {
		return gymDao.selectOneGymWithAsso(gymId);
	}
	
	@Override
	public List<Gym> getAllGymWithAsso() {
		return gymDao.selectAllGymWithAsso();
	}

	@Override
	public GymAndTrainerReviewDto gymGetOneByIdWithReview(long id) {
//		return new GymAndTrainerReviewDto();
		return gymDao.selectOneWithReview(id);
	}


	
	@Override
	public List<Gym> getAllGym() {
		return gymDao.selectAllGym();
	}

//	@Override
//	public int registGym(long userId, long gymId, long priceId) {
//		PriceDayDto priceDayDto = priceDao.selectPriceOneByPriceId(priceId);
//		return gymDao.insertRegistUser(userId, gymId, priceId, priceDayDto.getDays());
//	}
//	
//	@Override
//	public int quitGym(long userId, long gymId) {
//		return gymDao.deleteRegistUser(userId, gymId);
//	}

	@Override
	public int favGym(long gymId, long loginUserId) {
		return gymDao.insertFavGym(gymId, loginUserId);
	}

	@Override
	public int unfavGym(long gymId, long loginUserId) {
		return gymDao.deleteFavGym(gymId, loginUserId);
	}

	@Override
	public List<Gym> findFavGymByUserId(long userId) {
		return gymDao.selectAllFavGym(userId);
	}

	@Override
	public List<GymExpiredDto> findMyGymByUserId(long userId) {
		// TODO Auto-generated method stub
		log.info("list={}", gymDao.selectAllMyGym(userId));
		return gymDao.selectAllMyGym(userId);
	}

}
