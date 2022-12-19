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
	
	// 게시글 작성
	
	@Override
	public void write(BoardVO boardVO) throws Exception {
		dao.write(boardVO);
	}
    
	//게시판 목록 조회
	
	@Override
	public List<BoardVO> list(Criteria cri) throws Exception {
		return dao.list(cri);
	}
	
	//게시물 총 갯수
	@Override
	public int listCount() throws Exception {
		return dao.listCount();
	}
	
	//게시물 상세 조회
	
	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}
	
	// 게시물 수정
	@Override
	public void update(BoardVO boardVO) throws Exception {
	    dao.update(boardVO);
	}
	
	//회원탈퇴
	
    @Override
	public void delete(int bno) throws Exception {
	    dao.delete(bno);
	}

}
