package com.kh.busan.api.model.service;

import java.util.List;

import com.kh.busan.api.model.vo.CommentDTO;

public interface BusanService {
String getBusan(int page);
	
	String getBusanDetail(int pk);
	
	void save(CommentDTO comment);
	
	List<CommentDTO> getComment(Long foodNo);
}
