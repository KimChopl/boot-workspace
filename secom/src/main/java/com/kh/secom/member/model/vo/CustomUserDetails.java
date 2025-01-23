package com.kh.secom.member.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class CustomUserDetails implements UserDetails {

	/*
	 * 1. 필드에 vo삽입
	 * 2. 필요한 값을 필드에 넣어줌
	 */
	
	private Long userNo;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	
}
