package com.ssafy.fitnect.payment;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitnect.model.dao.OrderDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderDao orderDao;
	
//	@Override
//	public TotalInfoDto getOrderOne(long orderId) {
//		return orderDao.selectOrderOne;
//	}
	
	@Override
	public List<TotalInfoDto> getOrderAll() {
		return orderDao.selectOrderAll();
	}
	
	@Override
	public int addOrder(TotalInfoDto totalInfoDto) {
		return orderDao.insertOrder(totalInfoDto);
	}
}
