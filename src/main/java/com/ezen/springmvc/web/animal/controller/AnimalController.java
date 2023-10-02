package com.ezen.springmvc.web.animal.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.springmvc.domain.animal.AnimalDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/animal")
@RequiredArgsConstructor
@Slf4j
public class AnimalController {
	
	@GetMapping("")
	public String dog(Model model) {
		
		return "animal/list";
	}
	
	// 회원 데이터 검증 -  #3. Bean Validation 사용
	@PostMapping("")
	@ResponseBody
	public Object register(@RequestBody AnimalDTO animalDTO, BindingResult bindingResult) {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
		try {
			urlBuilder.append("/" +  URLEncoder.encode("63514341636475643131356265526c65","UTF-8") );
			urlBuilder.append("/" +  URLEncoder.encode("xml","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("CardSubwayStatsNew","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
			urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
			urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		if (bindingResult.hasErrors()) {
			// bindingResult를 모델에 저장하고, 타임리프 HTML 템플릿 페이지에서 렌더링하는 것이 아니라
			// bindingResult에 저장한 오류 메시지를 읽어와서 응답메시지 바디에 JSON으로 출력한다.
			return bindingResult.getAllErrors();
		}
		
		// 데이터 검증 성공 시 DB 저장 후 응답메시지 바디에 회원정보를 JSON으로 출력한다.
		
		return animalDTO;
	}
	
}
