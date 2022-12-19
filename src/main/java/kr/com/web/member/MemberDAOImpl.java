package kr.com.web.member;

import java.lang.reflect.Member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
    
	@Inject
	public SqlSession sqlsession;
	
	//ȸ������
	@Override
	public int register(MemberVO vo) throws Exception{ 
		return sqlsession.insert("memberMapper.register", vo);
	}
	
	//ȸ�����Ծ��̵��ߺ�üũ
	@Override
	public int idchk(MemberVO vo) throws Exception{ 
		int result = sqlsession.selectOne("memberMapper.idchk", vo);
		return result;
	}
	
	// �н����� üũ
	@Override
	public int passchk(MemberVO vo) throws Exception {
		int result = sqlsession.selectOne("memberMapper.passchk", vo);
		return result;
	}
	
	//�α���
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		return sqlsession.selectOne("memberMapper.login", vo);
	}

	//ȸ������
	@Override
	public int memberUpdate(MemberVO vo) throws Exception{
		return sqlsession.update("memberMapper.update", vo); 
	}
		
	//ȸ��Ż��
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		sqlsession.delete("memberMapper.delete", vo);
	}
	
	
	
}
