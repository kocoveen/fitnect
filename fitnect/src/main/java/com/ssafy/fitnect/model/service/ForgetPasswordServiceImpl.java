package com.ssafy.fitnect.model.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.fitnect.model.dao.UserDao;
import com.ssafy.fitnect.model.dto.UserEmailNameDto;
import com.ssafy.fitnect.model.dto.Users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class ForgetPasswordServiceImpl implements ForgetPasswordService {
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserDao userDao;
	

	@Override
	public String sendPassword(UserEmailNameDto userEmailNameDto) {
		String tempPass = getTempPassword();
		updatePassword(userEmailNameDto.getEmail(), tempPass);
		return tempPass;
	}
    
    public void updatePassword(String userEmail, String tempPass) {
    	Users user = userDao.getUserByEmail(userEmail);
    	user.setPassword(passwordEncoder.encode(tempPass));
    	userDao.updateUser(user);
    }


    public String getTempPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String tempPass = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            tempPass += charSet[idx];
        }
        return tempPass;
    }



}
