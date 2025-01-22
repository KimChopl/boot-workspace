package com.kh.busan.api.model.service;

import java.util.List;

import com.kh.busan.api.model.vo.CommtentDTO;

public interface BusanService {
String getBusan(int page);
	
	String getBusanDetail(int pk);
	
	void save(CommtentDTO comment);
	
	List<CommtentDTO> getComment(Long foodNo);
}
