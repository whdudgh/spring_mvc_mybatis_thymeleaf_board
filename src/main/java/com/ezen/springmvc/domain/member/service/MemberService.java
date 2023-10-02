package com.ezen.springmvc.domain.member.service;

import java.util.List;

import com.ezen.springmvc.domain.member.dto.Member;

/**
 * 회원 관련 비지니스 로직 처리 및 트랜잭션 관리
 * @author 조영호
 *
 */
public interface MemberService {
	
	public void register(Member member);
	
	public Member isMember(String name, String passwd);
	
	public List<Member> getMemberList();
	
	public Member getMember(String id);

}
