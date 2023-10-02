package com.ezen.springmvc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.mapper.ArticleMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ArticleMapperTest {
	
	@Autowired
	private ArticleMapper articleMapper;
	
//	@Test
//	@DisplayName("전체 게시판 조회")
//	@Disabled
//	 전체 게시판 조회 재헌(2023-08-09)
//	public void findAllTest(){
//		log.info("hi : {}", articleMapper);
//		List<ArticleDTO> list = articleMapper.findAll();
//		for (ArticleDTO articleDTO : list) {
//			System.out.println(articleDTO);
//		}
//	}
	
	@Test
	@DisplayName("게시글 첫 글 등록")
	@Transactional
	@Disabled
//	 게시글 등록 재헌(2023-08-09)
	void createTest() {
		ArticleDTO article = ArticleDTO.builder()
							.boardId(20)
							.writer("monday")
							.subject("게시글 제목 테스트")
							.content("게시글 내용 테스트")
							.passwd("1111")
							.levelNo(0)
							.orderNo(0)
							.build();
		articleMapper.create(article);
//		sqlSession.rollback();
		System.out.println("신규 게시글 등록 완료");
		assertThat(article)
			.isNotNull();
	}
	
	@Test
	@Transactional
	@DisplayName("게시글 댓글 등록")
	@Disabled
	// 댓글 등록 재헌(2023-08-09)
	void commentCreateTest() {
		ArticleDTO article = ArticleDTO.builder()
							.boardId(20)
							.writer("tuesday")
							.subject("게시글 댓글 제목 테스트1")
							.content("게시글 댓글 내용 테스트1")
							.passwd("1111")
							.groupNo(121)
							.levelNo(1)
							.build();
		articleMapper.commentCreate(article);
		
		System.out.println("신규 댓글 등록 완료");
		
		assertThat(article)
			.isNotNull();
	}
	
	@Test
	@DisplayName("게시글 상세보기")
//	@Disabled
	// 게시글 상세보기 영호(2023-08-09)
	public void readArticleTest() {
		
		//given
		int groupNo = 1;
		
		//when
		List<ArticleDTO> articleDTO = articleMapper.readArticle(groupNo);
		//여기서 클릭되었을 때 id받아 해당하는 글 의 hitcount업데이트 구현 필요
//		articleMapper.updateHitcount(articleId);
		
		//then
		for (ArticleDTO article : articleDTO) {
			log.info("가져온 게시글의 아티클 : {}",article);
		}
	}
	
	@Test
	@Transactional
	@DisplayName("오더넘버 수정")
	@Disabled
	//대댓글 달기전 오더넘버 밀어내기(보드카테고리와 댓글을 작성하려는 아티클의 아이디가 필요함.) 
	//대댓글보다 항상 먼저 실행되어야함.
	//영호(2023-08-09)
	public void updatePlusOrderNo() {
		
		//given
		int boardId = 10;
		int articleId = 2;
		
		//when
		boolean result = articleMapper.updatePlusOrderNo(articleId, boardId);
		
		//then
		assertThat(result).isTrue();
	}

	@Test
	@Transactional
	@DisplayName("대댓글 달기")
	@Disabled
	//대댓글 달기 테스트 영호(2023-08-09)
	public void setRReflyTest() {
		
		//given
		ArticleDTO articleDTO = ArticleDTO.builder()
										    .boardId(20)
										    .writer("monday")
										    .subject("monday의 새로달린 대댓글 입니다.")
										    .content("이사람 또왔내")
										    .passwd("1234")
										    .groupNo(1)
										    .levelNo(2)
										    .articleId(2)
										    .build();
		//when
		boolean result = articleMapper.setRReply(articleDTO);
		
		//then
		assertThat(result).isTrue();
		log.info("대댓글 등록 결과 : {}",result);
	}

	@Test
	@DisplayName("해당 게시글 삭제")
	@Disabled
	// 삭제기능 테스트 영호(2023-08-09)
	public void deleteArticleTest() {
		
		//given(get이나 post방식으로 선택된 articleId를 넘겨받았다 치고 작업함.)
		ArticleDTO articleDTO = ArticleDTO.builder()
											.passwd("1111")
											.articleId(1)
											.groupNo(1)
											.levelNo(0)
											.build();
		
		//when
		if(articleDTO.getLevelNo() > 0) {
			
			updateMinusOrderNoTest();
		}
		boolean result = articleMapper.deleteArticle(articleDTO);
		
		//then
		assertThat(result).isTrue();
		log.info("삭제 여부 : {}", result);
	}
	
	@Test
	@DisplayName("level 0 이외의 level삭제시 orderNo당기기")
	@Disabled
	public void updateMinusOrderNoTest() {
		//given
		int articleId = 10;
		int boardId = 10;
		//when
		boolean success = articleMapper.updateMinusOrderNo(articleId,boardId);
		//then
		assertThat(success).isTrue();
	}
}
