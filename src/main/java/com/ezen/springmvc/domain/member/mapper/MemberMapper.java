package com.ezen.springmvc.domain.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezen.springmvc.domain.member.dto.Member;
import com.ezen.springmvc.domain.member.dto.MemberSearchCondition;

/**
 * 멤버관련 맵퍼인터페이스
 * @author 조영호
 *
 */
@Mapper
public interface MemberMapper {
	
	//전체회원 불러오기
	public List<Member> findByAll();
	
	//회원상세조회
	public Member findById(String id);
	
	//회원찾기
	public Member findByIdAndPasswd(@Param("id") String id, @Param("passwd") String passwd);
	
	//like검색
	public List<Member> findByNameLike(String name);
	
	//회원등록
	public void create(Member member);
	
	//회원수정
	public void update(Member member);
	
	// 검색 타입별 회원 검색
	public List<Member> findBySearchType(@Param("type") String type, @Param("value") String value);
	
	// 통합 검색
	public List<Member> findBySearchAll(String value);
	
	// 통합 검색(체크한 조건에 따른 검색방법)
	public List<Member> findBySearchAllOption(MemberSearchCondition searchCondition);
	
}
