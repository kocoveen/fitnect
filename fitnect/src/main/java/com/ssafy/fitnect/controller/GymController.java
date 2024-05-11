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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.GymAndTrainerReviewDto;
import com.ssafy.fitnect.model.dto.ReviewGym;
import com.ssafy.fitnect.model.dto.ReviewGymSaveDto;
import com.ssafy.fitnect.model.dto.ReviewGymUpdateDto;
import com.ssafy.fitnect.model.service.GymService;
import com.ssafy.fitnect.model.service.ReviewService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gym")
public class GymController {
	
	private final GymService gymService;
	private final ReviewService reviewService;
	
	
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
	
	@GetMapping("/{id}")
	public ResponseEntity<?> gymGetOne(@PathVariable("id") long id) {
		GymAndTrainerReviewDto result = gymService.gymGetOneByIdWithReview(id);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/{gymId}/review-with-trainer-review")
	public ResponseEntity<?> selectAllReviewTrainer(@PathVariable("gymId") long gymId) throws Exception {
		List<ReviewGym> result = reviewService.findAllReviewGymById(gymId);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{gymId}/review/{id}")
	public ResponseEntity<?> selectOneReviewTrainer(@PathVariable("gymId") long gymId, @PathVariable("id") long id) throws Exception {
		ReviewGym result = reviewService.findOneReviewGymById(id);
		return ResponseEntity.ok(result);
	}
	
	
	@PostMapping("/{gymId}/review")
	public ResponseEntity<?> insertReviewGym(@PathVariable("gymId") long gymId, @RequestBody ReviewGymSaveDto reviewGym) throws Exception {
		reviewGym.setGymId(gymId);
		int result = reviewService.writeReviewGym(reviewGym);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{gymId}/review/{id}")
	public ResponseEntity<?> updateReviewGym(@PathVariable("gymId") long gymId, @PathVariable("id") long id, @RequestBody ReviewGymUpdateDto reviewGym) throws Exception {
		reviewGym.setGymId(gymId);
		reviewGym.setReviewGymId(id);
		int result = reviewService.modifyReviewGym(reviewGym);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{gymId}/review/{id}")
	public ResponseEntity<?> updateReviewGym(@PathVariable("gymId") long gymId, @PathVariable("id") long id) throws Exception {
		int result = reviewService.removeReviewGym(id);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
	}
	
	
	@Data
	private class Result<T> {
		private T result;

		public Result(T result) {
			this.result = result;
		}
		
	}
}
