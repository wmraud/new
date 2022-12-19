package kr.com.web.member;

import javax.inject.Inject;


import org.springframework.stereotype.Service;

@Service // 여기다가 왜 저거 쓴거임 
public class MemberServiceImpl implements MemberService {
	
	@Inject MemberDAO dao;
	
	//회원가입
	
    public int register(MemberVO vo) throws Exception {
		return dao.register(vo);
	}
    
    //회원가입아이디중복체크
    
    @Override
    public int idchk(MemberVO vo) throws Exception {
    	int result = dao.idchk(vo);
    	return result;
    }
    
    // 패스워드 체크
    @Override
    public int passchk(MemberVO vo) throws Exception {
    	int result = dao.passchk(vo);
    	return result;
    }
	
    //로그인
    
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
	//회원수정
	
	@Override
	public int memberUpdate(MemberVO vo) throws Exception {
		//받은 vo를 DAO로 보내줍니다.
		return dao.memberUpdate(vo);
		
	}
	
	//회원탈퇴
	
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		dao.memberDelete(vo);
	}
	
}