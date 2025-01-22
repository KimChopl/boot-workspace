package com.kh.busan.api;

public class NewGenerationLogin {

	/*
	 * 	GET - 조회
	 * 	POST - 등록
	 * 	PUT - 수정
	 * 	DELETE - 삭제
	 * 
	 */
	
	/*
	 * 	로그인 시
	 * 	받은 아이디와 비밀번호를 DB의 정보와 일치하는지 검증
	 * 	검증 실패 시 예외 발생
	 * 	성공 시 JWT 토큰 발행 -> 이때 사용자의 정보를 담아 만듬
	 * 	클라이언트 응답
	 * 	응답 받은 토큰을 사용자 저장소에 저장
	 * 	이후 인증이 필요한 요청 시 마다(로그인 시 사용가능한 기능 사용시) 토큰을 포함해서 요청을 보냄
	 * 	받은 토근을 유효한 토큰인지 검증
	 * 	잘못된 토큰 => 예외 발생
	 * 	유효한 토큰 => 기능 구현
	 * 
	 */
}
