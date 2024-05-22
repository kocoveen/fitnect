package com.ssafy.fitnect.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitnect.annotation.AuthRequired;
import com.ssafy.fitnect.auth.TokenDto;
import com.ssafy.fitnect.auth.TokenProvider;
import com.ssafy.fitnect.model.dto.Classes;
import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.GymExpiredDto;
import com.ssafy.fitnect.model.dto.ReviewGym;
import com.ssafy.fitnect.model.dto.ReviewTrainer;
import com.ssafy.fitnect.model.dto.UserEmailNameDto;
import com.ssafy.fitnect.model.dto.UserLoginDto;
import com.ssafy.fitnect.model.dto.UserSignUpRequestDto;
import com.ssafy.fitnect.model.dto.Users;
import com.ssafy.fitnect.model.dto.UsersUpdatePasswordDto;
import com.ssafy.fitnect.model.service.ClassService;
import com.ssafy.fitnect.model.service.ForgetPasswordService;
import com.ssafy.fitnect.model.service.GymService;
import com.ssafy.fitnect.model.service.ReviewService;
import com.ssafy.fitnect.model.service.UserService;
import com.ssafy.fitnect.util.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
	
	private final UserService userService;
	private final GymService gymService;
	private final ClassService classService;
	private final ReviewService reviewService;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
	private final ForgetPasswordService forgetPasswordService;
	
	private final PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> detail(@PathVariable("id") long id, @AuthenticationPrincipal User loginUser) throws Exception {
		if (id == getLoginUserId(loginUser)) {
			Users user = userService.getUserById(id);
			return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, user));			
		}
		return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));	
	}
	
    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
    	
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        
        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행 및 db와 대조하여 인증

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // 인증받은 새로운 authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        TokenDto token = tokenProvider.createAccessToken(authentication);
        
		// Front End와 합의사항
		//1.) accessToken, refreshToken => 본문에 둘다 보내도 되고,
		//   refreshToken: localStorage에 저장, accessToken: sessionStorage 또는 pinia store에만.
       
        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add("accessToken", token.getAccessToken());
        httpHeaders.add("location","http://localhost:5173");

        // tokenDto를 이용해 response body에도 넣어서 리턴
        return ResponseEntity.ok().headers(httpHeaders).body(ApiResponse.success(HttpStatus.OK, token));
    }

	@PostMapping("/sign-up")
	public ResponseEntity<?> signup(@RequestBody UserSignUpRequestDto user) throws Exception {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		int result = userService.insert(user);
		if (result == 1) {
			return ResponseEntity.created(null).body(ApiResponse.success(HttpStatus.CREATED, result));
		} else {
			return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
		}
	}
	
	@PostMapping("/sign-out")
	public ResponseEntity<?> signout() {
		return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, "로그아웃 완료"));
	}
	
	@PutMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody UsersUpdatePasswordDto userIdAndPassword, @AuthenticationPrincipal User loginUser) throws Exception {
		
		userIdAndPassword.setPassword(passwordEncoder.encode(userIdAndPassword.getPassword()));
		
		int result = userService.changePassword(userIdAndPassword);
		if (result == 1) {
			return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, result));
		} else {
			return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
		}
	}	

	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Users user, @AuthenticationPrincipal User loginUser) throws Exception {
		log.info("loginUser={}", loginUser);
		log.info("user={}", user);
		
		user.setUserId(getLoginUserId(loginUser));
		int result = userService.update(user);
		
		if (result == 1) {
			return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, result));
		} else {
			return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id, @AuthenticationPrincipal User loginUser) throws Exception {
		if (id == getLoginUserId(loginUser)) {
			int result = userService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success(HttpStatus.NO_CONTENT, result));
		} else {
			return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."));
		}
	}
	
	
	@AuthRequired
	@GetMapping("/fav-gym")
	public ResponseEntity<?> getFavGym(@AuthenticationPrincipal User loginUser) throws Exception {
		
		long loginUserId = getLoginUserId(loginUser);
		List<Gym> result = gymService.findFavGymByUserId(loginUserId);
		
		return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, result));
		
	}
	
	
	@GetMapping("/my-gym")
	public ResponseEntity<?> getMyGym(@AuthenticationPrincipal User loginUser) throws Exception {
		
		long loginUserId = getLoginUserId(loginUser);
		
		List<GymExpiredDto> result = gymService.findMyGymByUserId(loginUserId);
		return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, result));
	}
	
	@GetMapping("/my-class")
	public ResponseEntity<?> getMyClass(@AuthenticationPrincipal User loginUser) throws Exception {
		
		long loginUserId = getLoginUserId(loginUser);
		
		List<Classes> result = classService.findAllClassByUserId(loginUserId);
		return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, result));
	}
	
	@GetMapping("/my-gym-review")
	public ResponseEntity<?> getMyGymReview(@AuthenticationPrincipal User loginUser) throws Exception {
		
		long loginUserId = getLoginUserId(loginUser);
		log.info("user={}", loginUser);
		log.info("user={}", loginUserId);
		
		List<ReviewGym> result = reviewService.findAllGymReviewByUserId(loginUserId);
		return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, result));
	}
	
	@GetMapping("/my-trainer-review")
	public ResponseEntity<?> getMyTrainerReview(@AuthenticationPrincipal User loginUser) throws Exception {
		
		long loginUserId = getLoginUserId(loginUser);
		
		List<ReviewTrainer> result = reviewService.findAllTrainerReviewByUserId(loginUserId);
		return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, result));
	}
	
	@PostMapping("/find-password")
	public ResponseEntity<?> findPassword(@RequestBody UserEmailNameDto userEmailNameDto) {
		
		if (userService.isUserEmailNameEqauls(userEmailNameDto)) {
			String result = forgetPasswordService.sendPassword(userEmailNameDto);
			return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, result));
		} else {
			return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.NOT_ACCEPTABLE, "이메일과 이름을 다시 확인해주세요."));
		}
		
	}
	
	
	private long getLoginUserId(User user) {
		return userService.getUserByEmail(user.getUsername()).getUserId();
	}

}
