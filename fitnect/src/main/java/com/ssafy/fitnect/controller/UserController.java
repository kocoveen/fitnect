package com.ssafy.fitnect.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitnect.annotation.AuthRequired;
import com.ssafy.fitnect.auth.CustomUserDetails;
import com.ssafy.fitnect.auth.TokenDto;
import com.ssafy.fitnect.auth.TokenProvider;
import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.UserLoginDto;
import com.ssafy.fitnect.model.dto.UserSignUpRequestDto;
import com.ssafy.fitnect.model.dto.Users;
import com.ssafy.fitnect.model.service.GymService;
import com.ssafy.fitnect.model.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
	
	private final UserService userService;
	private final GymService gymService;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
	private final PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> detail(@PathVariable("id") long id) throws Exception {
		Users user = userService.getUserById(id);
		return ResponseEntity.ok(user);
		
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
        httpHeaders.add("location","http://localhost:5137");

        // tokenDto를 이용해 response body에도 넣어서 리턴
        return new ResponseEntity<>(token, httpHeaders, HttpStatus.OK);
    }

	@PostMapping("/sign-up")
	public ResponseEntity<?> insert(@ModelAttribute UserSignUpRequestDto user) throws Exception {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		int result = userService.insert(user);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, Users user) throws Exception {
		user.setUserId(id);
		int result = userService.update(user);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) throws Exception {
		int result = userService.delete(id);
		return new ResponseEntity<>(result, result == 1 ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
	}
	
	
	@AuthRequired
	@GetMapping("/fav-gym")
	public ResponseEntity<?> getFavGym() throws Exception {
		
		long loginUserId = getLoginUserId();
		
		List<Gym> favGyms = gymService.findFavGymByUserId(loginUserId);
		return new ResponseEntity<>(favGyms, favGyms.size() == 0 ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/my-gym")
	public ResponseEntity<?> getMyGym() throws Exception {
		
		long loginUserId = getLoginUserId();
		
		List<Gym> favGyms = gymService.findFavGymByUserId(loginUserId);
		return new ResponseEntity<>(favGyms, favGyms.size() == 0 ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
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
