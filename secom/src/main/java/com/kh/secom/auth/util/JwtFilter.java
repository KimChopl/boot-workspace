package com.kh.secom.auth.util;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kh.secom.auth.service.UserServiceImpl;
import com.kh.secom.exception.AccessTokenExpiredException;
import com.kh.secom.exception.JwtTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{ // 요청 한 번에 필터 한 번 작동하게 하기 위한 상속 -> 상속 받았기 때문에 순서를 지정하지 않아도 동작함

	private final JwtUtil jwtUtil;
	private final UserServiceImpl us;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authorization == null || !authorization.startsWith("Bearer")) {
			log.error("authorization이 존재하지 않습니다.");
			filterChain.doFilter(request, response);
			return;
		}
		// 토큰 자르기
		String token = authorization.split(" ")[1];
		try {
			
			Claims claims =  jwtUtil.parseJwt(token);
			
			String username = claims.getSubject();
			
			
			UserDetails userDetails = us.loadUserByUsername(username); // 시큐리티 컨택스트 홀더에 담기 위해 자료형을 맞춰줌(인증된 사용자의 정보)
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			// 토큰기반 인증에서는 여기까지 오면 인증이 이미 되었단 뜻이므로 두 번째 값에 null 삽입함.
			
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // 세부 설정, 원격 주소, MAC 주소, 세션 ID 등등
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		} catch(ExpiredJwtException e) {
			log.info("토큰 만료");
			//throw new AccessTokenExpiredException("토큰 만료"); 
			// 클라이언트에게 응답이 가지 않음. => 여기는 필터 영역임(Servlet Container 영역에 있음) -> 예외 처리를 하지 못함.
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // response 객체에 담아서 보내면 됨
			response.getWriter().write("Expired Token");
			return;
		} catch(JwtException e) {
			log.info("검증 실패");
			//throw new JwtTokenException("검증 실패");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
			response.getWriter().write("Expired Token");
			return;
		}
		
		filterChain.doFilter(request, response);
		
	}

}
