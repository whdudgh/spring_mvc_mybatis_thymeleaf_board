package com.ezen.springmvc.web.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springmvc.domain.member.dto.Member;
import com.ezen.springmvc.domain.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberControllerV2 {
	
	private final MemberService memberService;
		
	@PostMapping("/login_action")
	public String loginAction(@RequestParam("id") String id, @RequestParam("passwd") String passwd, HttpSession session,Model model) {
		Member member = memberService.isMember(id, passwd);
		log.info("들어온 아이디 : {}",id);
		log.info("들어온 비밀번호 : {}",passwd);
		log.info("찾아온 회원 : {}",member);
		if(member != null) {
			session.setAttribute("member", member);			
		} else {
			log.info("{}에 해당하는 회원이 없습니다.",id);
		}
		return "redirect:/article/list";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "member/login";
	}

}
