package com.kh.secom.member.model.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChangePasswordDTO {
	
	@NotBlank(message="비밀번호 입력")
	private String beforePwd;
	@NotBlank(message="비밀번호 입력")
	private String changePwd;
}
