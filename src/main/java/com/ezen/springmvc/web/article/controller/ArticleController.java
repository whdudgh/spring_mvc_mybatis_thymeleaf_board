package com.ezen.springmvc.web.article.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.article.service.ArticleService;
import com.ezen.springmvc.domain.common.web.dto.PageParams;
import com.ezen.springmvc.domain.common.web.dto.Pagination;
import com.ezen.springmvc.domain.member.dto.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
	
	private final ArticleService articleService;
		
	private static final int ELEMENT_SIZE = 10;
	
	private static final int PAGE_SIZE = 5;
	
	@GetMapping("/list")
	public String articleList(@RequestParam(value = "boardId", defaultValue = "10") int boardId, @RequestParam(value = "requestPage" , defaultValue = "1") int requestPage, HttpSession session,Model model) {
		
//		log.info("요청으로 들어온 boardId = {}",boardId);
		// 페이징 계산을 위한 게시글 전체 갯수 조회
		int rowCount = articleService.getCountAllForPaging(boardId);
		
//		log.info("객체생성전 요청 들어온 리퀘스트페이지 : {}",requestPage);
		// 전체 페이지 수 계산
		PageParams pageParams = PageParams.builder()
											.elementSize(ELEMENT_SIZE)
											.pageSize(PAGE_SIZE)
											.requestPage(requestPage)
											.rowCount(rowCount)
											.build();

		Pagination pagination = new Pagination(pageParams);
		int groupNo = 1;
		List<ArticleDTO> list = articleService.getAllArticleListForPaging(pageParams, boardId);
//		log.info("들어온 아티클들 {}",list);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		model.addAttribute("boardId", boardId);
		session.setAttribute("boardId",boardId);
		return "article/list";
	}
	
	@GetMapping("/write")
	public String createArticle(Model model) {
		return "article/write";
	}
	
	@PostMapping("/newarticle")
	public String insertArticle(@RequestParam("subject") String subject, @RequestParam("content") String content, @RequestParam("boardId") int boardId,@RequestParam("passwd") String passwd, HttpSession session,Model model) {
		log.info("받은 제목 : {}",subject);
		log.info("받은 내용 : {}",content);	
		log.info("받은 보드 아이디 : {}",boardId);
		log.info("받은 패스워드 : {}",passwd);
//		String writer = "melon";
		Member member = (Member)session.getAttribute("member");
		String writer = member.getId();
		ArticleDTO articleDTO = ArticleDTO.builder()
											.boardId(boardId)
											.content(content)
											.subject(subject)
											.passwd(passwd)
											.writer(writer)
											.build();
		articleService.createArticle(articleDTO);
		return "redirect:list";
	}
	
	@GetMapping("/read")
	public String readDetail(@RequestParam("groupNo") int groupNo, @RequestParam("levelNo") int levelNo, HttpSession session,Model model) {
		
		List<ArticleDTO> articleList = articleService.detailArticle(groupNo);
		Member member = (Member)session.getAttribute("member");
		int boardId = (int)session.getAttribute("boardId");
//		log.info("세션에 저장된 회원 정보 : {}",member);
//		log.info("세션에 저장된 보드아이디 : {}",boardId);
//		log.info("전달받은 그룹번호 : {}",groupNo);
//		log.info("전달받은 레벨번호 : {}",levelNo);
//		for (ArticleDTO articleDTO : articleList) {
//			log.info("들어온 아티클 : {}", articleDTO);
//		}
		model.addAttribute("member", member);
		model.addAttribute("articleList", articleList);
		session.setAttribute("groupNo", groupNo);
		session.setAttribute("levelNo", levelNo);
		return "article/read";
	}
	
	@PostMapping("/comment")
	public String insertComment(@RequestParam("writer") String writer, @RequestParam("content") String content ,@RequestParam("passwd") String passwd,
			@RequestParam("subject") String subject,HttpSession session ,Model model) {

		int boardId = (int)session.getAttribute("boardId");
		int levelNo = (int)session.getAttribute("levelNo");
		int groupNo = (int)session.getAttribute("groupNo");

		ArticleDTO comment = ArticleDTO.builder()
										.writer(writer)
										.subject(subject)
										.content(content)
										.passwd(passwd)
										.boardId(boardId)
										.levelNo(levelNo)
										.groupNo(groupNo)
										.build();
		articleService.newCommentCreate(comment);
		
		//		session.removeAttribute("levelNo");//		session.removeAttribute("groupNo");
		return "redirect:read?groupNo="+groupNo+"&levelNo="+levelNo;
	}
	
	@PostMapping("/reply")
	public String insertReply(@RequestParam("writer") String writer, @RequestParam("content") String content ,@RequestParam("passwd") String passwd,
			@RequestParam("subject") String subject, @RequestParam("articleId") int articleId,HttpSession session ,Model model) {

		int boardId = (int)session.getAttribute("boardId");
		int levelNo = (int)session.getAttribute("levelNo");
		int groupNo = (int)session.getAttribute("groupNo");
		log.info("들어온 아티클Id {}",articleId);
		ArticleDTO comment = ArticleDTO.builder()
										.articleId(articleId)
										.writer(writer)
										.subject(subject)
										.content(content)
										.passwd(passwd)
										.boardId(boardId)
										.levelNo(levelNo)
										.groupNo(groupNo)
										.build();
		log.info("들어온 작성자 : {}",writer);
		log.info("들어온 제목 : {}",subject);
		log.info("들어온 내용 : {}",content);
		log.info("들어온 비밀번호 : {}",passwd);
		log.info("들어온 보드Id : {}",boardId);
		log.info("들어온 레벨Id : {}",levelNo);
		log.info("들어온 그룹Id : {}",groupNo);
		articleService.createReply(comment);
		
		
//		session.removeAttribute("levelNo");
//		session.removeAttribute("groupNo");
		return "redirect:read?groupNo="+groupNo+"&levelNo="+levelNo;
	}
	
}
