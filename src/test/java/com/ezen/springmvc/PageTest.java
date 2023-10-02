package com.ezen.springmvc;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.common.web.dto.PageParams;
import com.ezen.springmvc.domain.common.web.mapper.PaginationMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PageTest {

	@Autowired
	private PaginationMapper paginationMapper;	

	@Test
	@Disabled
	@DisplayName("전체 게시판글 갯수")
	void findAllCountTest() {
		int boardId = 10;
		log.info("==================== 전체 게시판 조회 ========================");
		int count = paginationMapper.findAllCount(boardId);
		log.info("전체 게시판 갯수는 : {}", count);
	}
	
	@Test
//	@Disabled
	@DisplayName("검색된 전체 목록")
	void getAllArticle() {
		List<ArticleDTO> list = paginationMapper.finAllArticle();
		for (ArticleDTO articleDTO : list) {
			log.info("가져온 아티클 : {}",articleDTO);
		}
	}
	
	@Test
	@Disabled
	@DisplayName("검색 게시글 갯수")
	void findSearchCountTest() {
		log.info("==================== 검색 게시판 조회 ========================");
		
//		Map<String, Object> searchParams = new HashMap<String, Object>();
//		searchParams.put("type", "id");
//		searchParams.put("boardId", 10);
		
//		searchParams.put("type", "name");
//		searchParams.put("value", "테스트");
		
		String searchValue = "테스트";
		
		int count = paginationMapper.findSearchCount(searchValue);
		log.info("검색 게시글 갯수는 : {}", count);
	}
	
	@Test
	@DisplayName("검색된 글 정보")
	@Disabled
	void findSearchPageTest() {
		
//		Map<String, Object> searchParams = new HashMap<String, Object>();
//		searchParams.put("type", "id");
//		searchParams.put("value", 150);

//		searchParams.put("type", "name");
//		searchParams.put("value", "테스트");
		
		//위에껀 어떤게시판에서 특정값 검색할 때
		//내가 하고싶은건 전체게시판 특정값 검색
		
		String searchValue = "테스트";
		
		List<ArticleDTO> pages = paginationMapper.findSearchPage(searchValue);
		for (ArticleDTO articleDTO : pages) {
			log.info("검색된 글 정보 : {}", articleDTO);
		}
	}
	
	@Test
	@DisplayName("계층형 게시판 및 페이징 처리를 위한 게시글 목록")
	@Disabled
	void findSearchArticleTest() {
		
//		Map<String, Object> searchParams = new HashMap<String, Object>();
//		
//		searchParams.put("rows", rows);
//		searchParams.put("boardId", boardId);
		
		PageParams pageParams = PageParams.builder()
				.elementSize(15)
				.requestPage(2)
				.build();
		int boardId = 10;
		
		List<ArticleDTO> pages = paginationMapper.findSearchArticle(pageParams, boardId);
		for (ArticleDTO articleDTO : pages) {
			log.info("검색된 계층형 페이지 정보 : {}", articleDTO);
		}
	}
	
//	@Test
//	@DisplayName("페이징 처리 테스트")
////	@Disabled
//	void paginationTest() {
//		//given
//		PageParams pageParams = PageParams.builder()
//											.elementSize(15)
//											.requestPage(2)
//											.build();
//		int boardId = 10;
//		//when
//		findSearchArticleTest(pageParams, boardId);
//	}

}
