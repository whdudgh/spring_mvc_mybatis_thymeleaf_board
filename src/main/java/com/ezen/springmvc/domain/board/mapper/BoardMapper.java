package com.ezen.springmvc.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.springmvc.domain.board.dto.BoardDTO;

/**
 * board 테이블 관련 명세
 */
@Mapper
public interface BoardMapper {
	
	/** 신규 게시판 생성 */
	public void create(BoardDTO board);
	
	/** 전체 게시판 목록 반환 */
	public List<BoardDTO> findAll();
	
	/**
	 * 재헌 추가 (2023-08-09)
	 * @param updateBoard
	 * 게시판 수정
	 */
	public void updateBoard(BoardDTO updateBoard);

}
