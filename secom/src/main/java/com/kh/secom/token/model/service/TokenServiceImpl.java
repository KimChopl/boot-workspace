package com.kh.secom.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.secom.auth.util.JwtUtil;
import com.kh.secom.token.model.dto.RefreshTokenDTO;
import com.kh.secom.token.model.mapper.TokenMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final JwtUtil jwt;
	private final TokenMapper tm;

	@Override
	public Map<String, String> generateToken(String username, Long userNo) {
		Map<String, String> map = createToken(username);
		saveToken(map.get("refreshToken"), userNo);
		return map;
	}

	private Map<String, String> createToken(String username) {
		String accessToken = jwt.getAccessToken(username);
		String refreshToken = jwt.getRefrshToken(username);
		Map<String, String> map = new HashMap<String, String>();
		map.put("accessToken", accessToken);
		map.put("refreshToken", refreshToken);
		return map;
	}

	private void saveToken(String rfreshToken, Long userNo) {
		RefreshTokenDTO refreshToken = RefreshTokenDTO.builder().userNo(userNo).token(rfreshToken)
				.expiration((Long)(System.currentTimeMillis() + 3600000L * 72)).build();
		tm.saveToken(refreshToken);
	}

}
