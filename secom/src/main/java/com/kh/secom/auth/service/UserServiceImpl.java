package com.kh.secom.auth.service;


import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.secom.member.model.mapper.MemberMapper;
import com.kh.secom.member.model.vo.CustomUserDetails;
import com.kh.secom.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService{
	// AuthenticationManager가 실질적으로 사용자의 정보를 조회하는데 사용할 클래스

	private final MemberMapper mm;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		Member member = mm.findByUserId(username);
		
		if(member == null) {
			throw new UsernameNotFoundException("존재하지 않는 사용자");
		}
		
		return CustomUserDetails.builder().username(member.getUserId()).userNo(member.getUserNo())
				.password(member.getUserPwd()).authorities(Collections.singletonList(new SimpleGrantedAuthority(member.getRole()))).build();
	}

}
