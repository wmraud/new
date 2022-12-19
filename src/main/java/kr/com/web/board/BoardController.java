package kr.com.web.board;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.com.web.Criteria;
import kr.com.web.PageMaker;
import kr.com.web.member.MemberVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	// 게시판 글 작성 화면
	@RequestMapping(value = "/writeView", method = RequestMethod.GET)
	public String writeView(HttpSession session, MemberVO mvo) throws Exception{
		logger.info("writeView");
		
		mvo = (MemberVO) session.getAttribute("member");
		
		if(mvo != null) {
			return "board/writeView";
		}else
			return "redirect:/board/list";
	}
	
	// 게시판 글 작성
//	@RequestMapping(value = "/write", method = RequestMethod.POST)
//	public String write(BoardVO boardVO , MemberVO mvo , HttpSession session) throws Exception{
//		logger.info("write");
//		
//			service.write(boardVO);
//		return "redirect:/board/list";
//	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO boardVO , MemberVO mvo , HttpSession session) throws Exception{
		logger.info("write");
		
		mvo = (MemberVO)session.getAttribute("member");
		
		if(mvo != null) {
			service.write(boardVO);
			return "redirect:/";
		}else
		return "redirect:/board/list";
	}
	
	
	
	// 게시판 목록 조회
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model , Criteria cri) throws Exception{
		logger.info("list");
		
		model.addAttribute("list",service.list(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount());
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/list";
	}
	
	// 게시물 조회
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(BoardVO boardVO, Model model) throws Exception{
		logger.info("read");
		
		model.addAttribute("read", service.read(boardVO.getBno()));
		
		return "board/readView";
	}
	
	// 게시판 수정뷰
		@RequestMapping(value = "/updateView", method = RequestMethod.GET)
		public String updateView(BoardVO boardVO, Model model, MemberVO mvo , HttpSession session) throws Exception{
			logger.info("updateView");
			
			mvo = (MemberVO)session.getAttribute("member");
			
			if(mvo != null) {
			
			model.addAttribute("update", service.read(boardVO.getBno()));
			
			return "board/updateView";
		  }else
			  return "redirect:/board/list";
		}
	
	// 게시물 수정
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(BoardVO boardVO) throws Exception{
//		logger.info("update");
//		
//		return "redirect:/board/list"; 
//	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(BoardVO boardVO , MemberVO mvo , HttpSession session) throws Exception{
		logger.info("update");
		
		mvo = (MemberVO) session.getAttribute("member");
		
		if(mvo != null) {
	       if(mvo.getMemberNN().equals(boardVO.getWriter())) {
		      service.update(boardVO);
	       }
	    }
		return "redirect:/board/list";
	}
	
	
	// 게시물삭제 post
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(BoardVO bvo , MemberVO mvo , HttpSession session) throws Exception{
		logger.info("delete");
		
		mvo = (MemberVO) session.getAttribute("member");
		
		if(mvo != null) {
	       if(mvo.getMemberNN().equals(bvo.getWriter())) {
		      service.delete(bvo.getBno());
	       }
	    }
		return "redirect:/board/list";
	}
	
}