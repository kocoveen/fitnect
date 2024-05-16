package com.ssafy.fitnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitnect.auth.CustomUserDetails;
import com.ssafy.fitnect.model.dto.Classes;
import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.GymAndTrainerReviewDto;
import com.ssafy.fitnect.model.dto.ReviewGym;
import com.ssafy.fitnect.model.dto.ReviewGymSaveDto;
import com.ssafy.fitnect.model.dto.ReviewGymUpdateDto;
import com.ssafy.fitnect.model.dto.Users;
import com.ssafy.fitnect.model.service.ClassService;
import com.ssafy.fitnect.model.service.GymService;
import com.ssafy.fitnect.model.service.ReviewService;
import com.ssafy.fitnect.model.service.UserService;
import com.ssafy.fitnect.util.ApiResponse;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gym")
public class GymController {
	
	private final GymService gymService;
	private final ReviewService reviewService;
	private final UserService userService;
	private final ClassService classService;
	
	
	@GetMapping("")
	public ResponseEntity<?> gymAllList() {
		List<Gym> result = gymService.getAllGym();
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/with-asso")
	public ResponseEntity<?> gymAllListWithAsso() {
		List<Gym> result = gymService.getAllGymWithAsso();
		return ResponseEntity.ok()
				.body(ApiResponse.success(HttpStatus.OK, result));
	} 
	
	@GetMapping("/with-asso/{gymId}")
	public ResponseEntity<?> gymOneWithAsso(@PathVariable("gymId") long gymId) {
		Gym result = gymService.getOneGymWithAsso(gymId);
		return ResponseEntity.ok()
				.body(ApiResponse.success(HttpStatus.OK, result));
	}
	
	
	@GetMapping("/{gymId}/review-with-trainer-review")
	public ResponseEntity<?> selectAllReviewTrainer(@PathVariable("gymId") long gymId) throws Exception {
		List<ReviewGym> result = reviewService.findAllReviewGymById(gymId);
		return ResponseEntity.ok()
				.body(ApiResponse.success(HttpStatus.OK, result));
	}
	
	@GetMapping("/{gymId}/review")
	public ResponseEntity<?> gymGetOneIWithReview(@PathVariable("gymId") long gymId) {
		GymAndTrainerReviewDto result = gymService.gymGetOneByIdWithReview(gymId);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/{gymId}/review/{id}")
	public ResponseEntity<?> selectOneReviewTrainer(@PathVariable("gymId") long gymId, @PathVariable("id") long id) throws Exception {
		ReviewGym result = reviewService.findOneReviewGymById(id);
		return ResponseEntity.ok()
				.body(ApiResponse.success(HttpStatus.OK, result));
	}
	
	
	@PostMapping("/{gymId}/review")
	public ResponseEntity<?> insertReviewGym(@PathVariable("gymId") long gymId, @RequestBody ReviewGymSaveDto reviewGym) throws Exception {
		reviewGym.setGymId(gymId);
		reviewGym.setUserId(getLoginUserId());
		int result = reviewService.writeReviewGym(reviewGym);
		
		if (result == 1) {
			return ResponseEntity.created(null).body(ApiResponse.success(HttpStatus.CREATED, result));
		} else {
			return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
		}
	}
	
	@PutMapping("/{gymId}/review/{id}")
	public ResponseEntity<?> updateReviewGym(@PathVariable("gymId") long gymId, @PathVariable("id") long id, @RequestBody ReviewGymUpdateDto reviewGym) throws Exception {
		if (getLoginUserId() == id) {
			reviewGym.setGymId(gymId);
			reviewGym.setReviewGymId(id);
			int result = reviewService.modifyReviewGym(reviewGym);
			
			if (result == 1) {
				return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, result));
			} else {
				return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
			}
		}
		return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "본인이 아닙니다."));
	}
	
	@DeleteMapping("/{gymId}/review/{id}")
	public ResponseEntity<?> updateReviewGym(@PathVariable("gymId") long gymId, @PathVariable("id") long id) throws Exception {
		if (getLoginUserId() == id) {
		
			int result = reviewService.removeReviewGym(id);
			
			if (result == 1) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success(HttpStatus.OK, result));
			} else {
				return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
			}
		}
		return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "본인이 아닙니다."));
	}
	
	
	@PostMapping("/{gymId}/regist")
	public ResponseEntity<?> registGym(@PathVariable("gymId") long gymId, @RequestParam("priceId") long priceId) throws Exception {
				
		int result = gymService.registGym(getLoginUserId(), gymId, priceId);
		
		if (result == 1) {
			return ResponseEntity.created(null).body(ApiResponse.success(HttpStatus.CREATED, result));
		} else {
			return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
		}
	}
	
	@DeleteMapping("/{gymId}/quit")
	public ResponseEntity<?> quitGym(@PathVariable("gymId") long gymId) throws Exception {
		
		Users user = userService.getUserById(3);
		
		int result = gymService.quitGym(user.getUserId(), gymId);
		
		if (result == 1) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success(HttpStatus.OK, result));
		} else {
			return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
		}
	}
	
	@PostMapping("/{gymId}/fav")
	public ResponseEntity<?> favGym(@PathVariable("gymId") long gymId) throws Exception {
		
		int result = gymService.favGym(gymId, getLoginUserId());
		
		if (result == 1) {
			return ResponseEntity.created(null).body(ApiResponse.success(HttpStatus.CREATED, result));
		} else {
			return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
		}
	}
	
	@DeleteMapping("/{gymId}/unfav")
	public ResponseEntity<?> unfavGym(@PathVariable("gymId") long gymId) throws Exception {
		int result = gymService.unfavGym(gymId, getLoginUserId());
		
		if (result == 1) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success(HttpStatus.OK, result));
		} else {
			return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
		}
	}
	
	
	@GetMapping("/{gymId}/classes")
	public ResponseEntity<?> getClassesByGymId(@PathVariable("gymId") long gymId) throws Exception {
		List<Classes> classes = classService.findClassesByGymId(gymId);
		return ResponseEntity.ok()
				.body(ApiResponse.success(HttpStatus.OK, classes));
	}
	
	
	
	
	private long getLoginUserId() {
		return ( (CustomUserDetails) 
					( (UserDetails) SecurityContextHolder
									.getContext()
									.getAuthentication()
									.getPrincipal()
					)
				)
				.getUserId();
	}

}
