package com.kh.busan.api.model.vo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDTO implements Serializable {
	private Long foodNo;
	private String content;
	@NotBlank(message = "뭐함? 작성자 안씀?")
	private String writer;
	
	

}
