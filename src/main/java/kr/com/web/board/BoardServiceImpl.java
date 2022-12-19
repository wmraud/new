package kr.com.web.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.com.web.Criteria;
import kr.com.web.member.MemberVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDao dao;
	
	// �Խñ� �ۼ�
	
	@Override
	public void write(BoardVO boardVO) throws Exception {
		dao.write(boardVO);
	}
    
	//�Խ��� ��� ��ȸ
	
	@Override
	public List<BoardVO> list(Criteria cri) throws Exception {
		return dao.list(cri);
	}
	
	//�Խù� �� ����
	@Override
	public int listCount() throws Exception {
		return dao.listCount();
	}
	
	//�Խù� �� ��ȸ
	
	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}
	
	// �Խù� ����
	@Override
	public void update(BoardVO boardVO) throws Exception {
	    dao.update(boardVO);
	}
	
	//ȸ��Ż��
	
    @Override
	public void delete(int bno) throws Exception {
	    dao.delete(bno);
	}

}
