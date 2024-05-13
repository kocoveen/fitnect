package com.ssafy.fitnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		return ResponseEntity.ok().body(result);
	} 
	
	@GetMapping("/with-asso/{gymId}")
	public ResponseEntity<?> gymOneWithAsso(@PathVariable("gymId") long gymId) {
		Gym result = gymService.getOneGymWithAsso(gymId);
		return ResponseEntity.ok().body(result);
	}
	
	
	@GetMapping("/{gymId}/review-with-trainer-review")
	public ResponseEntity<?> selectAllReviewTrainer(@PathVariable("gymId") long gymId) throws Exception {
		List<ReviewGym> result = reviewService.findAllReviewGymById(gymId);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{gymId}/review")
	public ResponseEntity<?> gymGetOneIWithReview(@PathVariable("gymId") long gymId) {
		GymAndTrainerReviewDto result = gymService.gymGetOneByIdWithReview(gymId);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/{gymId}/review/{id}")
	public ResponseEntity<?> selectOneReviewTrainer(@PathVariable("gymId") long gymId, @PathVariable("id") long id) throws Exception {
		ReviewGym result = reviewService.findOneReviewGymById(id);
		return ResponseEntity.ok(result);
	}
	
	
	@PostMapping("/{gymId}/review")
	public ResponseEntity<?> insertReviewGym(@PathVariable("gymId") long gymId, @RequestBody ReviewGymSaveDto reviewGym) throws Exception {
		reviewGym.setGymId(gymId);
		reviewGym.setUserId(getLoginUserId());
		int result = reviewService.writeReviewGym(reviewGym);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{gymId}/review/{id}")
	public ResponseEntity<?> updateReviewGym(@PathVariable("gymId") long gymId, @PathVariable("id") long id, @RequestBody ReviewGymUpdateDto reviewGym) throws Exception {
		if (getLoginUserId() == reviewService.findOneReviewTrainerById(id).getUserId()) {
		
			reviewGym.setGymId(gymId);
			reviewGym.setReviewGymId(id);
			int result = reviewService.modifyReviewGym(reviewGym);
			return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("본인이 아닙니다.", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{gymId}/review/{id}")
	public ResponseEntity<?> updateReviewGym(@PathVariable("gymId") long gymId, @PathVariable("id") long id) throws Exception {
		if (getLoginUserId() == reviewService.findOneReviewTrainerById(id).getUserId()) {
		
			int result = reviewService.removeReviewGym(id);
			return new ResponseEntity<>(result, result == 1 ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("본인이 아닙니다.", HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping("/{gymId}/regist")
	public ResponseEntity<?> registGym(@PathVariable("gymId") long gymId, @RequestParam("priceId") long priceId) throws Exception {
				
		int result = gymService.registGym(getLoginUserId(), gymId, priceId);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
	}
	
//	@DeleteMapping("/{gymId}/quit")
//	public ResponseEntity<?> registGym(@PathVariable("gymId") long gymId) throws Exception {
//		
//		Users user = userService.getUserById(3);
//		
//		int result = gymService.registGym(user.getUserId(), gymId);
//		return new ResponseEntity<>(result, result == 1 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
//	}
	
	
	@GetMapping("/{gymId}/classes")
	public ResponseEntity<?> getClassesByGymId(@PathVariable("gymId") long gymId) throws Exception {
		List<Classes> classes = classService.findClassesByGymId(gymId);
		return ResponseEntity.ok(classes);
	}
	
	
	private long getLoginUserId() {
		return userService.getUserById(3).getUserId();
	}

	
	@Data
	private class Result<T> {
		private T result;

		public Result(T result) {
			this.result = result;
		}
		
	}

}
