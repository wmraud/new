package kr.com.web.board;

import java.util.List;

import kr.com.web.Criteria;

public interface BoardDao {

	// �Խñ� �ۼ�
	public void write(BoardVO boardVO) throws Exception;
	
	// �Խ��� ���
	public List<BoardVO> list(Criteria cri) throws Exception;
	
	// �Խù� �� ����
	public int listCount() throws Exception;
	
	// �Խù� ��ȸ
	public BoardVO read(int bno) throws Exception;
	
	// �Խù� ����
	public void update(BoardVO boardVO) throws Exception;
	
	// �Խù� ����
	public void delete(int bno)throws Exception;
}
