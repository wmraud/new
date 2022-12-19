package kr.com.web.member;

public interface MemberService {
	
	//회원가입
	
	public int register(MemberVO vo) throws Exception;
	
	//회원가입아이디중복체크
	
	public int idchk(MemberVO vo) throws Exception;
	
	//패스워드체크
	
	public int passchk(MemberVO vo) throws Exception;

	//로그인

	public MemberVO login(MemberVO vo) throws Exception;
	
	//회원수정
	
	public int memberUpdate(MemberVO vo) throws Exception;
	
	//회원탈퇴
	
	public void memberDelete(MemberVO vo) throws Exception;
	
}
