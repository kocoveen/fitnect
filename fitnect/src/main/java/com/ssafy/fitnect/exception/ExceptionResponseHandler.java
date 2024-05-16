package com.ssafy.fitnect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ssafy.fitnect.util.ApiResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class ExceptionResponseHandler {
    //...
	
//	@ExceptionHandler()
	
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> handleSignatureException() {
        return ResponseEntity
        		.status(HttpStatus.UNAUTHORIZED)
        		.body(ApiResponse.error(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다."));
    }
 
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handleMalformedJwtException() {
        return ResponseEntity
        		.status(HttpStatus.UNAUTHORIZED)
        		.body(ApiResponse.error(HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰입니다."));
    }
 
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException() {
        return ResponseEntity
        		.status(HttpStatus.UNAUTHORIZED)
        		.body(ApiResponse.error(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다. 다시 로그인해주세요."));
    }
    
    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<?> handleTokenNotFoundJwtException() {
        return ResponseEntity
        		.status(HttpStatus.UNAUTHORIZED)
        		.body(ApiResponse.error(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다."));
    }

}