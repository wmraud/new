package kr.com.web.member;

public interface MemberService {
	
	//ȸ������
	
	public int register(MemberVO vo) throws Exception;
	
	//ȸ�����Ծ��̵��ߺ�üũ
	
	public int idchk(MemberVO vo) throws Exception;
	
	//�н�����üũ
	
	public int passchk(MemberVO vo) throws Exception;

	//�α���

	public MemberVO login(MemberVO vo) throws Exception;
	
	//ȸ������
	
	public int memberUpdate(MemberVO vo) throws Exception;
	
	//ȸ��Ż��
	
	public void memberDelete(MemberVO vo) throws Exception;
	
}
