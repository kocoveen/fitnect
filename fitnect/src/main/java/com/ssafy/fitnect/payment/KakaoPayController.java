package com.ssafy.fitnect.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitnect.model.dto.Classes;
import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.Prices;
import com.ssafy.fitnect.model.dto.Users;
import com.ssafy.fitnect.model.service.GymService;
import com.ssafy.fitnect.util.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final RegistService registService;
    private final GymService gymService;
    
    private final OrderService orderService;
    
    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public ResponseEntity<?> readyToKakaoPay(@RequestBody PaymentInfoDto paymentInfoDto) {
    	
    	log.info("info={}", paymentInfoDto);
    	
    	
    	Prices price = registService.findGymPriceById(paymentInfoDto.getPriceId());
    	Classes classes = registService.findClassPriceById(paymentInfoDto.getClassId());
    	Gym gym = gymService.findGymOneById(paymentInfoDto.getGymId());
    	
    	long priceId = paymentInfoDto.getPriceId();
    	long gymId = paymentInfoDto.getGymId();
    	int days = price.getDays();
    	
    	long classId = paymentInfoDto.getClassId();

    	long userId = paymentInfoDto.getUserId();
//    	Users user = registService.findUserById(paymentInfoDto.getUserId());
    	
    	TotalInfoDto totalInfoDto = TotalInfoDto.builder()
    											.userId(userId)
    											.itemName(getItemName(gym.getName(), price.getPriceName(), classes.getClassName()))
    											.totalPrice(getTotalPrice(price.getPrice(), classes.getClassPrice()))
    											.build();
    	
    	orderService.addOrder(totalInfoDto);
    	
    	log.info("userId={}", totalInfoDto.getUserId());
    	log.info("orderId={}", totalInfoDto.getOrderId());
    	
    	KakaoReadyResponse response = kakaoPayService.kakaoPayReady(totalInfoDto);
    	
    	registService.registGym(new RegistGymDto(userId, gymId, priceId, days));
    	registService.registClass(new RegistClassDto(userId, classId));
    	
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, response));
    }
    
    private long getTotalPrice(long price, long classPrice) {
		return price + classPrice;
	}

	private String getItemName(String gymName, String priceName, String className) {
		/* 강의 이름 없을 때 null 고려 필요 */
		return "[" + gymName  + "]_[" + priceName + "]_[" + className + "]";
	}

	/**
     * 결제 성공
     */
    @GetMapping("/success/{userId}/{orderId}")
    public ResponseEntity<?> afterPayRequest(@RequestParam("pg_token") String pgToken, 
								    		@PathVariable("userId") long userId, 
								    		@PathVariable("orderId") long orderId) 
    {

        KakaoApproveResponse response = kakaoPayService.approveResponse(pgToken, userId, orderId);

        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, response));
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public void cancel() {

//        throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {

//        throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
    }
}