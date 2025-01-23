package com.kh.secom.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class Member { // VO에서 setter와 기본생성자는 만들지 않음(정석적인 방법)

	private Long userNo;
	private String userId;
	private String userPwd;
	private String role;
	
}
