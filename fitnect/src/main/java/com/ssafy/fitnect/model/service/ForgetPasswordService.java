package com.ssafy.fitnect.model.service;

import com.ssafy.fitnect.model.dto.UserEmailNameDto;

public interface ForgetPasswordService {
	String sendPassword(UserEmailNameDto userEmailNameDto);
}
