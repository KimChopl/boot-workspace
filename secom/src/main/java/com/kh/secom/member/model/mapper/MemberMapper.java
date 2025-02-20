package com.kh.secom.member.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.kh.secom.member.model.vo.Member;
import com.kh.secom.member.model.vo.MemberDTO;

@Mapper
public interface MemberMapper {

	Member findByUserId(String userId);

	@Insert("INSERT INTO TB_MEMBER VALUES (SEQ_MNO.NEXTVAL, #{userId}, #{userPwd}, #{role})")
	void save(Member member);

	@Update("UPDATE TB_MEMBER SET USER_PWD=#{password} WHERE USER_NO=#{userNo}")
	void changePassword(Map<String, String> changeMap);

}
