package com.kh.secom.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.secom.exception.DuplicateUserException;
import com.kh.secom.exception.InvalidParameterException;
import com.kh.secom.exception.MismatchPasswordException;
import com.kh.secom.member.model.mapper.MemberMapper;
import com.kh.secom.member.model.vo.ChangePasswordDTO;
import com.kh.secom.member.model.vo.CustomUserDetails;
import com.kh.secom.member.model.vo.Member;
import com.kh.secom.member.model.vo.MemberDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mm;
	private final PasswordEncoder pwe;

	@Override
	public void save(MemberDTO requestMember) {
		if("".equals(requestMember.getUserId()) || "".equals(requestMember.getUserPwd())) {
			throw new InvalidParameterException("유효하지 않은 값");
		}
		Member searched = mm.findByUserId(requestMember.getUserId());
		if(searched != null) {
			throw new DuplicateUserException("존재하는 아이디");
		}
		
		Member member = Member.builder().userId(requestMember.getUserId()).userPwd(pwe.encode(requestMember.getUserPwd())).role("ROLE_USER").build();
		
		mm.save(member);
		log.info("insert 성공");
	}

	@Override
	public void changePassword(ChangePasswordDTO passwords) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		
		if(!(pwe.matches(passwords.getBeforePwd(), user.getPassword()))) {
			throw new MismatchPasswordException("비밀번호 확인");
		}
		String changePwd = pwe.encode(passwords.getChangePwd());
		Map<String, String> changeMap = new HashMap<String, String>();
		changeMap.put("userNo", String.valueOf(user.getUserNo()));
		changeMap.put("password", changePwd);
		log.info("{}", changeMap);
		mm.changePassword(changeMap);
		log.info("업데이트 성콩");
	}
}
