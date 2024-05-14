package com.ssafy.fitnect.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ssafy.fitnect.annotation.AuthRequired;
import com.ssafy.fitnect.model.dto.Gym;
import com.ssafy.fitnect.model.dto.UserLoginDto;
import com.ssafy.fitnect.model.dto.Users;
import com.ssafy.fitnect.model.service.GymService;
import com.ssafy.fitnect.model.service.UserService;
import com.ssafy.fitnect.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/user")
@RestController
//@RequiredArgsConstructor
@Slf4j
public class UserController {
	
	private final UserService userService;
	private final GymService gymService;
	private final JwtUtil jwtUtil;
	
	@Autowired
	public UserController(UserService userService, GymService gymService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.gymService = gymService;
		this.jwtUtil = jwtUtil;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> detail(@PathVariable("id") long id) throws Exception {
		Users user = userService.getUserById(id);
		return ResponseEntity.ok(user);
		
	}
	
    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
    	
        try {
        	Map<String, Object> result = new HashMap<>();
        	
            Users user = userService.getUserByEmail(userLoginDto.getEmail());
            
            if (user == null || !user.getPassword().equals(user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일이나 패스워드 오류입니다.");
            }
            
            String accessToken = jwtUtil.createAccessToken(user.getUserId());
            
    		result.put("accessToken", accessToken);
    		result.put("name", user.getName());
    		result.put("userId", user.getUserId());
    		
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
            
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
        }
        
    }

	@PostMapping("/sign-up")
	public ResponseEntity<?> insert(Users user) throws Exception {
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
		return userService.getUserById(3).getUserId();
	}

}
