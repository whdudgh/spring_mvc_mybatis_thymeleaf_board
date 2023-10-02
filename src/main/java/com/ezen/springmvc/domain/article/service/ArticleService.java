package com.ezen.springmvc.domain.article.service;

import java.util.List;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.common.web.dto.PageParams;

/**
 * 게시판관련 서비스 명세
 * @author 조영호
 *
 */
public interface ArticleService {
	
	//-----------------Insert-----------------------
	//신규 게시글 등록
	public void createArticle(ArticleDTO articleDTO);
	
	//신규 댓글 등록
	public void newCommentCreate(ArticleDTO articleDTO);
	
	//신규 대댓글 등록(Update항목의 PlusOrderNo먼저)
	public boolean createReply(ArticleDTO articleDTO);
	
	//-----------------Select-----------------------
	//페이징에 사용할 게시글 전체 갯수 반환
	public int getCountAllForPaging(int boardId);
	
	//검색 갯수 반환
	public int getCountSearchForPaging(String searchValue);

	//검색된 목록 반환
	public List<ArticleDTO> findsearchArticle(String searchValue);
	
	//검색되지 않은 전체 목록 반환
	public List<ArticleDTO> getAllArticleListForPaging(PageParams pageParams, int boardId);
	
	//페이징처리 안된 전체 목록 반환
	public List<ArticleDTO> getAllArticleList();
	
	//게시글 상세보기
	public List<ArticleDTO> detailArticle(int groupNo);
	
	//-----------------Update, Delete---------------	
	//삭제기능
	public boolean deleteArticle(ArticleDTO articleDTO);
	
	//수정기능(구현필요)
	
}
