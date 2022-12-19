package kr.com.web.member;

import javax.inject.Inject;


import org.springframework.stereotype.Service;

@Service // ����ٰ� �� ���� ������ 
public class MemberServiceImpl implements MemberService {
	
	@Inject MemberDAO dao;
	
	//ȸ������
	
    public int register(MemberVO vo) throws Exception {
		return dao.register(vo);
	}
    
    //ȸ�����Ծ��̵��ߺ�üũ
    
    @Override
    public int idchk(MemberVO vo) throws Exception {
    	int result = dao.idchk(vo);
    	return result;
    }
    
    // �н����� üũ
    @Override
    public int passchk(MemberVO vo) throws Exception {
    	int result = dao.passchk(vo);
    	return result;
    }
	
    //�α���
    
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
	//ȸ������
	
	@Override
	public int memberUpdate(MemberVO vo) throws Exception {
		//���� vo�� DAO�� �����ݴϴ�.
		return dao.memberUpdate(vo);
		
	}
	
	//ȸ��Ż��
	
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		dao.memberDelete(vo);
	}
	
}