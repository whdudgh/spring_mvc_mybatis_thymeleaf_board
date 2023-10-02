package com.ezen.springmvc.domain.common.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.common.web.dto.PageParams;

@Mapper
public interface PaginationMapper {
	/** 게시판 전체 갯수 출력 */
	public int findAllCount(int boardId);
	
	/** 검색된 게시판 갯수 출력 */
//	public int findSearchCount(Map<String, Object> searchArticle);
	public int findSearchCount(String searchValue);
	
	/** 검색된 게시판 게시글 출력 */
//	public List<ArticleDTO> findSearchPage(Map<String, Object> searchArticle);
	public List<ArticleDTO> findSearchPage(String searchValue);
	
	/** 페이징 상관없는 목록 출력 */
	public List<ArticleDTO> finAllArticle();

	/** 계층형 게시판 및 페이징 처리를 위한 게시글목록 조회 */
	public List<ArticleDTO> findSearchArticle(@Param("pageParams") PageParams pageParams, @Param("boardId") int boardId);
}
