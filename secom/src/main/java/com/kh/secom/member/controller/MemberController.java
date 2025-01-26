package com.kh.secom.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.secom.auth.service.AuthenticationService;
import com.kh.secom.member.model.service.MemberService;
import com.kh.secom.member.model.vo.ChangePasswordDTO;
import com.kh.secom.member.model.vo.LoginResponse;
import com.kh.secom.member.model.vo.MemberDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="members", produces="application/json; charset=UTF-8")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

	private final MemberService ms;
	private final AuthenticationService as;

	// 새로운 데이터를 만들 때 요청 = post
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody MemberDTO requestMember) {

		// log.info("{}", requestMember);

		ms.save(requestMember);

		return ResponseEntity.ok("회원 가입 성공");
	}

	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody MemberDTO requestMember) {
		Map<String, String> tokens = as.login(requestMember);
		
		LoginResponse response = LoginResponse.builder().username(requestMember.getUserId()).tokens(tokens).build();
		return ResponseEntity.ok(response);
		
	}

	
	@PutMapping
	public ResponseEntity<?> changePasswword(@Valid @RequestBody ChangePasswordDTO passwords){
		
		log.info("{}", passwords);
		ms.changePassword(passwords);
		
		return ResponseEntity.ok("비밀번호 변경 완료");
	}
	
}
