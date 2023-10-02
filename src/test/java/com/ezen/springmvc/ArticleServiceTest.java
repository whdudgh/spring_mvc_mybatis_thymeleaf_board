package com.ezen.springmvc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.article.service.ArticleService;
import com.ezen.springmvc.domain.board.mapper.ArticleMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ArticleServiceTest {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleMapper articleMapper;
	
//	@Autowired
//	private PaginationMapper paginationMapper;
	
	@Test
	@Disabled
	@DisplayName("게시글등록")
	public void createArticleTest() {
		//given
		ArticleDTO articleDTO = ArticleDTO.builder()
								.boardId(10)
								.writer("monday")
								.subject("게시글 제목 테스트")
								.content("게시글 내용 테스트")
								.passwd("1111")
								.levelNo(0)
								.orderNo(0)
								.build();
		//when
		articleService.createArticle(articleDTO);
		
		//then
	}
	
	@Test
	@Disabled
	@DisplayName("댓글등록")
	public void newCommentCreateTest() {
		
		//given
		ArticleDTO article = ArticleDTO.builder()
									.boardId(10)
									.writer("tuesday")
									.subject("게시글 댓글 제목 테스트1")
									.content("게시글 댓글 내용 테스트1")
									.passwd("1111")
									.groupNo(1)
									.levelNo(1)
									.build();
		//when
		articleService.newCommentCreate(article);
		//then
		
	}
	
	@Test
	@Disabled
	@DisplayName("대댓글달기")
	public void createReplyTest() {
		//given
		ArticleDTO articleDTO = ArticleDTO.builder()
			    						.boardId(10)
			    						.writer("monday")
			    						.subject("monday의 새로달린 대댓글 입니다.")
			    						.content("이사람 또왔내")
			    						.passwd("1234")	
			    						.groupNo(1)
			    						.levelNo(2)
			    						.articleId(2)
			    						.build();
		//when
		boolean updateP= articleMapper.updatePlusOrderNo(articleDTO.getArticleId(), articleDTO.getBoardId());
		boolean result = articleService.createReply(articleDTO);
		
		//then
		assertThat(result).isTrue();
		log.info("ORDER_NO 증가 실행 결과 : {}",updateP);
		log.info("대댓글 등록 실행 결과 : {}",result);
	}
	//====================게시판 조회 관련=============================
	public void pageTest() {
		
	}
	
	@Test
	@Disabled
	public void getAllArticleTest() {
		articleService.getAllArticleList();
	}
	
	@Test
//	@Disabled
	@DisplayName("페이징처리를 위한 보드별 목록 갯수 불러오기")
	public void getCountAllForPagingTest() {
		int boardId = 10;
		int result = articleService.getCountAllForPaging(boardId);
		log.info("가져온 목록 수 : {}",result);
	}
	
}
