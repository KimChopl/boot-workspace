package com.kh.busan.api.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kh.busan.api.model.service.BusanService;
import com.kh.busan.api.model.vo.CommentDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class BusanController {
	
private final BusanService bs;
	
	@GetMapping("/busan")
	public ResponseEntity<?> getBusanFood(@RequestParam(name="page")int page) throws UnsupportedEncodingException, URISyntaxException{
		
		String response = bs.getBusan(page);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/busan/{pk}")
	public ResponseEntity<?> getBusanDetail(@PathVariable(name="pk") int pk){
		String response = bs.getBusanDetail(pk);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/comments")
	public ResponseEntity<String> save(@RequestBody CommentDTO comment){
		bs.save(comment);
		return ResponseEntity.status(HttpStatus.CREATED).body("작성 완료"); // 200번으로 보내는게 아니라 201로 가야함(insert, create 등등 => 새로운 데이터를 만들었음)
	}
	
	@GetMapping("/comments/{id}")
	public ResponseEntity<List<CommentDTO>> getComments(@PathVariable(name="id") Long foodNo){
		List<CommentDTO> list = bs.getComment(foodNo);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("test/test")
	public void test() throws URISyntaxException, IOException {
		
		String url = "https://www.nifs.go.kr/bweb/OpenAPI_json?id=frcenterCode&key=qPwOeIrU-2501-QQLNAD-1023";
		URI uri = new URI(url);
		RestTemplate rt = new RestTemplate();
        String response =  rt.getForObject(uri, String.class);
        String a = new String(response.getBytes(Charset.forName("ISO-8859-1")), Charset.forName("EUC-KR"));
        log.info("{}", a);
	}

}
