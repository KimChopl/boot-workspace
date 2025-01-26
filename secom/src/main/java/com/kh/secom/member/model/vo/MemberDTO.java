package com.kh.secom.member.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {

	private Long userNo;

	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영어 또는 숫자만 사용 가능")
	@Size(min = 5, max = 15, message = "너무 길거나 너무 짧음 5~15자 까지 가능.")
	@NotBlank(message = "공백은 넣을 수 없음.")
	private String userId;

	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영어 또는 숫자만 사용 가능")
	@Size(min = 4, max = 25, message = "너무 길거나 너무 짧음 4~25자 까지 가능.")
	@NotBlank(message = "공백은 넣을 수 없음.")
	private String userPwd;

	private String role;

}
