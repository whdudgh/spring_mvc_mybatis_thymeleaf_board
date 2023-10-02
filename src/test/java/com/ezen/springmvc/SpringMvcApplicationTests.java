package com.ezen.springmvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
class SpringMvcApplicationTests {
	
//	private final MemberMapper memberMapper;
	
	@Test
//	@Disabled
	public void findByAllTest() {
		//given
		
		//when
//		List<Member> list = memberMapper.findByAll();
		
		//then
//		for (Member member : list) {
			log.info("111");
//		}
	}

}
