package com.kh.secom.member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64.Encoder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api")
@Slf4j
public class ApiSempleController {
	
	@GetMapping("fk")
	public ResponseEntity<?> fishKind() throws URISyntaxException, UnsupportedEncodingException {
		String key = "qPwOeIrU-2501-QQLNAD-1023";
		StringBuilder requestUrl = new StringBuilder("https://www.nifs.go.kr/OpenAPI_json?id=frcenterInfo");
		requestUrl.append("&key=" + key);
		requestUrl.append("&mf_s_cls_cd=" + URLEncoder.encode("광어", "UTF-8"));
		URI uri = new URI(requestUrl.toString());
		RestTemplate rt = new RestTemplate();
		String result = rt.getForObject(uri, String.class);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("fk1")
	public ResponseEntity<?> fishKind1() throws UnsupportedEncodingException, URISyntaxException{
		String key = "zFn1kbKPTOR9MMBejxx3t71%2FBLMSAUkr38XyEv1udh45VqBOaf8f%2BiIEnzZEXq9po6Cr17gda3eMaGYUA4PseQ%3D%3D";
		StringBuilder url = new StringBuilder("http://apis.data.go.kr/1192000/MarineLivingInfoService");
		url.append("?serviceKey=" + key);
		url.append("&commKorNm=" + URLEncoder.encode("우럭", "UTF-8"));
		URI uri = new URI(url.toString());
		RestTemplate rt = new RestTemplate();
		String result = rt.getForObject(uri, String.class);
		//String a = new String(result.getBytes(Charset.forName("ISO-8859-1")), Charset.forName("EUC-KR"));
		byte[] b = result.getBytes("ISO-8859-1");
		String response = new String(b, StandardCharsets.UTF_8);
			return ResponseEntity.ok(response);
	}
	
	@GetMapping("ship")
	public ResponseEntity<?> ship() throws URISyntaxException{
		String key = "NUwThH0qLKaKYkOJwPp5qw==";
		StringBuilder url = new StringBuilder("http://www.khoa.go.kr/api/oceangrid/preOcean/search.do");
		url.append("?ServiceKey=" + key);
		url.append("&Type=KOREA");
		url.append("&ResultType=json");
		URI uri = new URI(url.toString());
		RestTemplate rt = new RestTemplate();
		String result = rt.getForObject(uri, String.class);
		return ResponseEntity.ok(result);
	}
	
	private String sendRequest(StringBuilder url) throws URISyntaxException {
		URI uri = new URI(url.toString());
		RestTemplate rt = new RestTemplate();
		String result = rt.getForObject(uri, String.class);
		return result;
	}
	
	@GetMapping("weather")
	public ResponseEntity<?> weather() throws URISyntaxException{
		String key ="NUwThH0qLKaKYkOJwPp5qw==";
		StringBuilder url = new StringBuilder("http://www.khoa.go.kr/api/oceangrid/fcIndexOfType/search.do");
		url.append("?ServiceKey=" + key);
		url.append("&Type=SF");
		url.append("&ResultType=json");
		log.info("{}", url);
		String result = sendRequest(url);

		return ResponseEntity.ok(result);
	}

}
