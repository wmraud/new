package kr.com.web.member;

import java.lang.reflect.Member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
    
	@Inject
	public SqlSession sqlsession;
	
	//회원가입
	@Override
	public int register(MemberVO vo) throws Exception{ 
		return sqlsession.insert("memberMapper.register", vo);
	}
	
	//회원가입아이디중복체크
	@Override
	public int idchk(MemberVO vo) throws Exception{ 
		int result = sqlsession.selectOne("memberMapper.idchk", vo);
		return result;
	}
	
	// 패스워드 체크
	@Override
	public int passchk(MemberVO vo) throws Exception {
		int result = sqlsession.selectOne("memberMapper.passchk", vo);
		return result;
	}
	
	//로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		return sqlsession.selectOne("memberMapper.login", vo);
	}

	//회원수정
	@Override
	public int memberUpdate(MemberVO vo) throws Exception{
		return sqlsession.update("memberMapper.update", vo); 
	}
		
	//회원탈퇴
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		sqlsession.delete("memberMapper.delete", vo);
	}
	
	
	
}
