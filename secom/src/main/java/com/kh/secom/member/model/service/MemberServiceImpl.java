package com.kh.secom.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.secom.exception.DuplicateUserException;
import com.kh.secom.exception.InvalidParameterException;
import com.kh.secom.member.model.mapper.MemberMapper;
import com.kh.secom.member.model.vo.Member;
import com.kh.secom.member.model.vo.MemberDTO;

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
}
