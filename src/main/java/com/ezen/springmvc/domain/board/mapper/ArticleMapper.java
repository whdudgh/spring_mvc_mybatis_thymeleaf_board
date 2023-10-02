package com.ezen.springmvc.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;

/**
 * article 테이블 관련 명세
 */
@Mapper
public interface ArticleMapper {
	/** 신규 게시글 등록 #7번 sql사용함. */
	public void create(ArticleDTO articleDTO);
	
	/** 전체 게시글 목록 반환 연습삼아 먼저해봄.*/
//	public List<ArticleDTO> findAll();

	// 페이징 계산에 필요한 게시글 전체 갯수 반환
//	public int getCountAll();
	
	// 페이징 계산(검색값 포함)에 필요한 게시글 전체 갯수 반환 재헌 함.
//	public int getCountAll(PageParams pageParams);
	
	// 요청 페이지, 페이지당 보여지는 목록 갯수에 따른 목록 반환 재헌 함. 
//	public List<ArticleDTO> findByAll(PageParams pageParams);
	
	// 댓글 쓰기 기능
	public void commentCreate(ArticleDTO articleDTO);
	
	// 글 수정 기능(구현 필요.)
	public boolean updateArticle();
	
	// 글 상세보기 시 선택된 글(레벨 0인글)의 hitCount증가 기능(구현 필요.)
	public boolean updateHitCount();
	
	/**
	 * 조영호
	 * @param articleId
	 * 대댓글 적기 전 선택된게시판 id와 해당하는 글의 id를 받아와 order_no 증가
	 */
	public boolean updatePlusOrderNo(@Param("articleId") int articleId, @Param("boardId") int boardId);
	
	/**
	 * 조영호
	 * @param articleDTO
	 * @return 성공여부
	 * 대댓글 등록하기 (2023-08-09)
	 */
	public boolean setRReply(ArticleDTO articleDTO);
	
	/**
	 * 조영호
	 * @param groupNo
	 * @return 해당그룹의 모든 글
	 * 게시글 상세보기 (2023-08-09)
	 */
	public List<ArticleDTO> readArticle(int groupNo);
	
	/**
	 * 조영호
	 * @param passwd
	 * @param articleId
	 * @return 성공여부
	 * 게시글 삭제(댓글, 대댓글, 게시글) (2023-08-09)
	 * 삭제하려는 글의 레벨번호 받아서 0번이면 그룹째로 삭제 아니면 articleId로 삭제(아티클 아이디로 삭제시 orderNO수정 필요)
	 * 비밀번호, 아티클Id, group번호, levelNO을 ArticleDTO에 담아서 객체체로 넘겨버리자.
	 */
	public boolean deleteArticle(ArticleDTO articleDTO);
	
	//levelNo 0이상일때만 글을 삭제후 남아있는 group내의 orderNo을 다시 정렬해야하는 기능(Update) 구현 필요.
	public boolean updateMinusOrderNo(@Param("articleId") int articleId, @Param("boardId") int boardId);
	
}
