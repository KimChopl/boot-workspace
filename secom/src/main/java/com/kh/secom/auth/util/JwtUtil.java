package com.kh.secom.auth.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {
	
	// 애플리케이션 설정파일(application.properties / application.yml)에 정의된 속성의 값들을 @Vlaue애노테이션을 이용해서 주입받을 수 있음
	@Value("${jwt.secret}")
	private String secretKey;
	// 아래 필드로 JWT 서명에 사용할 수 있음.
	private SecretKey key;

	private long ACCESS_TOKEN_EXPIRED = 3600000L *24;
	private long REFRESH_TOKEN_EXPIRED = 3600000L * 72;
	
	@PostConstruct // 빈 초기화 시 필요한 추가 설정들을 할 수 있음.
	public void init() {
		byte[] keyArr = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyArr);
	}
	
	public String getAccessToken(String username) {
		
		return Jwts.builder().subject(username).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRED)).signWith(key).compact();
	}
	

}
