package com.kh.secom.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.secom.auth.service.AuthenticationService;
import com.kh.secom.member.model.service.MemberService;
import com.kh.secom.member.model.vo.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService ms;
	private final AuthenticationService as;
	
	// 새로운 데이터를 만들 때 요청 = post
	@PostMapping
	public ResponseEntity<?> save(@RequestBody MemberDTO requestMember){
		
		//log.info("{}", requestMember);
		
		ms.save(requestMember);
		
		return ResponseEntity.ok("회원 가입 성공");
	}
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody MemberDTO requestMember){
		as.login(requestMember);
		return null;
		
	}
	
}
