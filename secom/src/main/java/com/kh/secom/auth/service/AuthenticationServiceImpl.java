package com.kh.secom.auth.service;

import java.util.Map;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.kh.secom.auth.util.JwtUtil;
import com.kh.secom.member.model.vo.CustomUserDetails;
import com.kh.secom.member.model.vo.MemberDTO;
import com.kh.secom.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager am;
	//private final JwtUtil jwt;
	private final TokenService ts;
	
	@Override
	public Map<String, String> login(MemberDTO requestMember) {
			
		// 사용자 인증
		Authentication authentication =  am.authenticate(new UsernamePasswordAuthenticationToken(requestMember.getUserId(), requestMember.getUserPwd())); 
		// 이친구가 알아서 인증 해줌, 생성자 안에 사용자가 입력한 아이디와 비밀번호 인자 넣기
		/*
		 *  UserNamePasswordAuthenticationToken
		 *  
		 *  사용자가 입력한 username과 password를 검증하는 용도로 사용하는 클래스
		 *  주로 SpringSecurity에서 인증을 시도할 때 사용함
		 *  
		 *  입력한 아이디와 비밀번호가 authenticate 메소드로 전달됨
		 */
		log.info("로그인 성공");
		
		CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
		log.info("{}", user);
		
		Map<String, String> tokens = ts.generateToken(user.getUsername(), user.getUserNo());
		return tokens;
	
	}

}
