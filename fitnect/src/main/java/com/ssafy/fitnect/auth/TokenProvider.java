package com.ssafy.fitnect.auth;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.ssafy.fitnect.exception.TokenNotFoundException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class TokenProvider {
	
	@Value("${jwt.accesstoken.expiretime}")
	private Long accessTokenExpireTime;
	
	@Value("${jwt.refreshtoken.expiretime}")
	private Long refreshTokenExpireTime;
	
	private SecretKey secretKey;
	
	private static final String AUTHORITIES_KEY = "auth"; // 토큰 생성, 검증 위한 상수
	private static final String BEARER_TYPE = "bearer"; // 토큰 생성, 검증 위한 상수
	
	private TokenProvider(@Value("${jwt.key}") String jwtKey) {
		this.secretKey = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8)); // HS256, Base64
	}
	
	
	public TokenDto createAccessToken(Authentication authentication) {
		long currentTime = System.currentTimeMillis(); // 현재시간
		
		Date exp = new Date(currentTime + accessTokenExpireTime * 1000); // 1시간
		
		
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		String email = ((CustomUserDetails) authentication.getPrincipal()).getEmail();
		String name = ((CustomUserDetails) authentication.getPrincipal()).getName();
		long userId = ((CustomUserDetails) authentication.getPrincipal()).getUserId();
		double latitude = ((CustomUserDetails) authentication.getPrincipal()).getLatitude();
		double longitude = ((CustomUserDetails) authentication.getPrincipal()).getLongitude();
		
		String accessToken = Jwts.builder()
			.header().add("typ", "JWT").and()
			.claim(AUTHORITIES_KEY, authorities)
			.claim("name", name)
			.claim("userId", userId)
			.claim("lat", latitude)
			.claim("lon", longitude)
			.subject(email)
			.expiration(exp)
			.signWith(secretKey)
			.compact();
		
		
		return TokenDto.builder()
				.grantType(BEARER_TYPE)
				.accessToken(accessToken)
				.tokenExpiresIn(exp.getTime())
				.build();
	}
	
	
	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token).getPayload();
		
		
//		log.info("authorites = {}", claims.get(AUTHORITIES_KEY).toString().split(","));
		
        Collection<? extends GrantedAuthority> authorities = Collections.emptyList(); //authorities를 빈 리스트로 설정했으므로, 그대로 대입.
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}
	
	
	public void validateToken(String token) throws TokenNotFoundException {
		try {
			Jwts.parser().verifyWith(secretKey)
			.build()
			.parseSignedClaims(token);
		} catch (IllegalArgumentException e) {
			throw new TokenNotFoundException();
		}

	}

	
//	public String createRefreshToken(String userId) throws UnsupportedEncodingException {
//		long currentTime = System.currentTimeMillis(); // 현재시간
//		
//		/*
//		JwtBuilder jwtRefreshTokenBuilder = Jwts.builder();
//		
//		jwtRefreshTokenBuilder.claim("userId", userId);
//		jwtRefreshTokenBuilder.setIssuedAt(new Date(currentTime));
//		jwtRefreshTokenBuilder.setExpiration(new Date(currentTime + refreshTokenExpireTime*1000));
//		jwtRefreshTokenBuilder.signWith(SignatureAlgorithm.HS256, jwtKey.getBytes("UTF-8"));
//	
//		return jwtRefreshTokenBuilder.compact();
//		 */
//
//		Date exp = new Date(currentTime + refreshTokenExpireTime * 1000); // 1시간
//		return Jwts.builder()
//				.header().add("typ", "JWT").and()
//				.claim("id", userId).expiration(exp).signWith(secretKey)
//				.compact();
//	}
}

