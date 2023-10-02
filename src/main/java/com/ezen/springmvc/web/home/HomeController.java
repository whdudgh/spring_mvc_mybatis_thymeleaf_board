package com.ezen.springmvc.web.home;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 타임리프 테스트 컨트롤러
 * @author 조영호
 *
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping("")
	public String home(Model model) {
		return "index";
	}
	
}
