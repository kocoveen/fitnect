package com.ssafy.fitnect.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.fitnect.auth.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {
	
	private final TokenProvider tokenProvider;
//    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	

    @Value("${auth.whitelist}")
    private String[] AUTH_WHITELIST;
//  auth.whitelist=/, /swagger-ui/**, /api-docs, /swagger-ui-custom.html, /v3/api-docs/**, /api-docs/**, /swagger-ui.html, /api/v1/auth/**, /user/sign-up, /user/sign-in, /error
    
//    private static final String[] AUTH_WHITELIST = {
//	    "/", "/swagger-ui/**", "/api-docs", 
//	    "/swagger-ui-custom.html", "/v3/api-docs/**", 
//	    "/api-docs/**", "/swagger-ui.html", "/api/v1/auth/**", 
//	    "/user/sign-up", "/user/sign-in", "/error"
//    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(CsrfConfigurer<HttpSecurity>::disable)
//                .exceptionHandling((exceptionHandling) -> //컨트롤러의 예외처리를 담당하는 exception handler와는 다름.
//                	exceptionHandling
//                    	.accessDeniedHandler(jwtAccessDeniedHandler)
//                       .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//    			)
                .cors(c -> c.configurationSource( corsConfigurationSource() ))
                .headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin))	// H2 콘솔 사용을 위한 설정
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers(AUTH_WHITELIST).permitAll()	// requestMatchers의 인자로 전달된 url은 모두에게 허용
                                .anyRequest().authenticated()	// 그 외의 모든 요청은 인증 필요
                )
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))	// 세션을 사용하지 않으므로 STATELESS 설정
                .addFilterAfter(new JwtAuthenticationFilter(tokenProvider), BasicAuthenticationFilter.class)
                .exceptionHandling((exceptionHandling) -> //컨트롤러의 예외처리를 담당하는 exception handler와는 다름.
	            	exceptionHandling
	                    .accessDeniedHandler(jwtAccessDeniedHandler)
	                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
    			)
                .build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowedOrigins(List.of("http://localhost:5173", "smtp.gmail.com"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(List.of("*"));
		config.setExposedHeaders(List.of("Authorization"));
		config.setMaxAge(3600L);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
