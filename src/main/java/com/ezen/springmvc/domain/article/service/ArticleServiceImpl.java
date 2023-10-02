package com.ezen.springmvc.domain.article.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.mapper.ArticleMapper;
import com.ezen.springmvc.domain.common.web.dto.PageParams;
import com.ezen.springmvc.domain.common.web.mapper.PaginationMapper;

import lombok.RequiredArgsConstructor;

/**
 * ArticleService구현체
 * @author 조영호
 *
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{
	
	private final ArticleMapper articleMapper;
	
	private final PaginationMapper paginationMapper;
	
	//------------------------Insert---------------------------
	//신규글작성
	@Override
	@Transactional
	public void createArticle(ArticleDTO articleDTO) {
		articleMapper.create(articleDTO);
	}
	
	//신규 댓글 작성
	@Override
	@Transactional
	public void newCommentCreate(ArticleDTO articleDTO) {
//		articleMapper.updatePlusOrderNo(articleDTO.getArticleId(),articleDTO.getBoardId());
		articleMapper.commentCreate(articleDTO);
	}
	
	//신규 대댓글 작성
	@Override
	@Transactional
	public boolean createReply(ArticleDTO articleDTO) {
		articleMapper.updatePlusOrderNo(articleDTO.getArticleId(),articleDTO.getBoardId());
		return articleMapper.setRReply(articleDTO);
	}
	
	//-------------------Select-------------------------------
	//전체글 갯수반환
	@Override
	public int getCountAllForPaging(int boardId) {
		return paginationMapper.findAllCount(boardId);
	}
	//검색된 전체글 갯수반환
	@Override
	public int getCountSearchForPaging(String searchValue) {
		return paginationMapper.findSearchCount(searchValue);
	}
	//검색된 게시글들 반환 페이징처리안됨
	@Override
	public List<ArticleDTO> findsearchArticle(String searchValue){
		return paginationMapper.findSearchPage(searchValue);
	}
	//전체 게시글들 반환 페이징처리됨
	@Override
	public List<ArticleDTO> getAllArticleListForPaging(PageParams pageParams, int boardId){
		return paginationMapper.findSearchArticle(pageParams, boardId);
	}
	//게시글 상세페이지 반환
	@Override
	public List<ArticleDTO> detailArticle(int groupNo) {
		return articleMapper.readArticle(groupNo);
	}
	
	//---------------Delete--------------------------------
	//게시글 삭제
	@Override
	@Transactional
	public boolean deleteArticle(ArticleDTO articleDTO) {
		articleMapper.updateMinusOrderNo(articleDTO.getArticleId(), articleDTO.getBoardId());
		return articleMapper.deleteArticle(articleDTO);
	}
	
	@Override
	public List<ArticleDTO> getAllArticleList() {
		return paginationMapper.finAllArticle();
	}
	
}
