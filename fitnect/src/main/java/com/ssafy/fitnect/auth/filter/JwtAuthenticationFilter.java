package com.ssafy.fitnect.auth.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.ssafy.fitnect.auth.TokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Order(0)
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "bearer ";
    private final TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String accessToken = resolveToken(request);

			if (StringUtils.hasText(accessToken)) {
				
				tokenProvider.validateToken(accessToken); // void로 에러가 생기면 잡아서 보내는데 ExceptionResponseHandler가 처리
				
				log.info("validate ok");
				Authentication authentication = tokenProvider.getAuthentication(accessToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		} catch (Exception e) {
			log.info("exception message={}", e.getMessage());
			request.setAttribute("exception", e);
		}
		
		filterChain.doFilter(request, response);
		
//		String accessToken = resolveToken(request);
//		String requestURI = request.getRequestURI();
//		
//		if (StringUtils.hasText(accessToken) && tokenProvider.validateToken(accessToken)) {
//			Authentication authentication = tokenProvider.getAuthentication(accessToken);
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			
//		} else {
//			request.setAttribute("exception", e);
//		}
//		
//		filterChain.doFilter(request, response);
	}
	
	
    // Request Header 에서 토큰 정보를 꺼내오기 위한 메소드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }

        return null;
    }

}
