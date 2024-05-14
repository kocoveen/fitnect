package com.ssafy.fitnect.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

	
	@Value("${jwt.accesstoken.expiretime}")
	private Long accessTokenExpireTime;
	
	@Value("${jwt.refreshtoken.expiretime}")
	private Long refreshTokenExpireTime;
	
	private SecretKey secretKey;
	
	private JwtUtil(@Value("${jwt.key}") String jwtKey) {
		this.secretKey = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8)); // HS256
	}
	
	
	public String createAccessToken(long userId) throws UnsupportedEncodingException {
		long currentTime = System.currentTimeMillis(); // 현재시간
		
		Date exp = new Date(currentTime + accessTokenExpireTime * 1000); // 1시간
		return Jwts.builder()
				.header().add("typ", "JWT").and()
				.claim("id", userId).expiration(exp).signWith(secretKey)
				.compact();
	}
	
	public String createRefreshToken(String userId) throws UnsupportedEncodingException {
		long currentTime = System.currentTimeMillis(); // 현재시간
		
		/*
		JwtBuilder jwtRefreshTokenBuilder = Jwts.builder();
		
		jwtRefreshTokenBuilder.claim("userId", userId);
		jwtRefreshTokenBuilder.setIssuedAt(new Date(currentTime));
		jwtRefreshTokenBuilder.setExpiration(new Date(currentTime + refreshTokenExpireTime*1000));
		jwtRefreshTokenBuilder.signWith(SignatureAlgorithm.HS256, jwtKey.getBytes("UTF-8"));
	
		return jwtRefreshTokenBuilder.compact();
		 */

		Date exp = new Date(currentTime + refreshTokenExpireTime * 1000); // 1시간
		return Jwts.builder()
				.header().add("typ", "JWT").and()
				.claim("id", userId).expiration(exp).signWith(secretKey)
				.compact();
	}
	
	public boolean validCheck(String token){
		try {
//			System.out.println(token);
//			Jwts.parser().setSigningKey(jwtKey.getBytes("UTF-8")).parseClaimsJws(token);
			Jwts.parser().verifyWith(secretKey)
				.build()
				.parseSignedClaims(token);
		} catch(Exception e) { // token을 파싱하는데 에러가 발생했다면 유효한 토큰이 아님.
//			System.out.println(e);
			return false;
		}
//		System.out.println("valid");
		return true;
	}

}

