package kr.com.web.member;



import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	    
	
	    // ȸ������ get
		@RequestMapping(value = "/register", method = RequestMethod.GET)
		public String getregister(MemberVO vo , HttpSession session) {
			logger.info("getregisetr");
			
			vo = (MemberVO) session.getAttribute("member");
			
			if(vo != null) {
			  return "member/login";
			}else {
			return "member/register";
			}
		}
		
        // ȸ������ post
		@RequestMapping(value = "/register", method = RequestMethod.POST)
		public String postRegister(MemberVO vo) throws Exception {
			logger.info("post register");
			
			int result = service.idchk(vo);
			
			try {
				if(result == 1) {
					return "/member/register";
				}else if(result == 0) {
					
					String inputPass = vo.getMemberPW();
					String pwd = pwdEncoder.encode(inputPass);
					vo.setMemberPW(pwd);
			
					service.register(vo);
				}
			} catch (Exception e) {
				throw new RuntimeException();
			}
			return "redirect:/";
		}
		
		// ���̵� �ߺ� üũ
		@ResponseBody
		@RequestMapping(value="/idchk", method = RequestMethod.POST)
				public int idchk(MemberVO vo) throws Exception {
					int result = service.idchk(vo);
					return result;
				}
				
		// �н����� üũ
		@ResponseBody
		@RequestMapping(value="/passchk", method = RequestMethod.POST)
			public boolean passchk(MemberVO vo) throws Exception {
				
//			    int result = service.passchk(vo);
				
				MemberVO login = service.login(vo);	
				boolean pwchk = pwdEncoder.matches(vo.getMemberPW(), login.getMemberPW());
				
			return pwchk;
		}
				
       // �α���
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public String getlogin(MemberVO vo) {
			logger.info("getlogin");
		return "member/login";
		}
		
		// �α��� post
		        
				@RequestMapping(value = "/login", method = RequestMethod.POST)
				public String postlogin(MemberVO vo , Model model , HttpServletRequest req , RedirectAttributes rttr) throws Exception {
					logger.info("postlogin"); 
					
					HttpSession session = req.getSession();
					
					MemberVO login =  service.login(vo);
					
					boolean pwdmatch;
					if(login != null) {
					pwdmatch = pwdEncoder.matches(vo.getMemberPW(), login.getMemberPW());
					} else {
					pwdmatch = false;
					}

					if(login != null && pwdmatch == true) {
					session.setAttribute("member", login);
					} else {
					session.setAttribute("member", null);
					model.addAttribute("mes", "N");
					}
         			
					return "member/login";
				}
		
		//ȸ���������� get
				
		@RequestMapping(value="/updateView", method = RequestMethod.GET)
		public String updateView(MemberVO vo , HttpSession session){
			logger.info("updateView");
			
			vo = (MemberVO)session.getAttribute("member");
			
			if ( vo != null) {
	    	   return "member/updateView";
		    }
			else {
		       return "member/login";	
		    }
		}
		
				
		//ȸ���������� post
		@ResponseBody
		@RequestMapping(value="/update", method = RequestMethod.POST)
		public int MemberUpdate(MemberVO vo, HttpSession session) throws Exception{
            
			String inputPass = vo.getMemberPW();
			String pwd = pwdEncoder.encode(inputPass);
			vo.setMemberPW(pwd);
	
			int result = service.memberUpdate(vo);
			session.invalidate();
			
			return result;
		}
		
		
		// �α׾ƿ�
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public String logout(HttpSession session) throws Exception{
					
			session.invalidate();
					
			return "member/login";
		}
		
		// ȸ�� Ż�� get
		@RequestMapping(value="/deleteView", method = RequestMethod.GET)
		public String deleteView(MemberVO vo , HttpSession session) throws Exception{
			
			vo = (MemberVO)session.getAttribute("member");
			
			if(vo != null) {
			   return "member/deleteView";
			}
			else {
				return "member/login";
			}
		}
		
		// ȸ�� Ż�� post
		@RequestMapping(value="/delete", method = RequestMethod.POST)
		public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
			
//			// ���ǿ� �ִ� member�� ������ member������ �־��ݴϴ�.
//			MemberVO member = (MemberVO) session.getAttribute("member");
//			// ���ǿ��ִ� ��й�ȣ
//			String sessionPass = member.getMemberPW();
//			// vo�� ������ ��й�ȣ
//			String voPass = vo.getMemberPW();
//			
//			if(!(sessionPass.equals(voPass))) {
//				rttr.addFlashAttribute("msg", false);
//				return "redirect:/member/deleteView";
//			}
			service.memberDelete(vo);
			session.invalidate();
			return "redirect:/";
		}
				
		

}
