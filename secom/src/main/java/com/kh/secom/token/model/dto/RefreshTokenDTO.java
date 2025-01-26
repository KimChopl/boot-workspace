package com.kh.secom.token.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class RefreshTokenDTO {
	
	private String token;
	private Long userNo;
	private Long expiration;

}
