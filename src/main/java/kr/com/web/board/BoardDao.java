package kr.com.web.board;

import java.util.List;

import kr.com.web.Criteria;

public interface BoardDao {

	// 게시글 작성
	public void write(BoardVO boardVO) throws Exception;
	
	// 게시판 목록
	public List<BoardVO> list(Criteria cri) throws Exception;
	
	// 게시물 총 갯수
	public int listCount() throws Exception;
	
	// 게시물 조회
	public BoardVO read(int bno) throws Exception;
	
	// 게시물 수정
	public void update(BoardVO boardVO) throws Exception;
	
	// 게시물 삭제
	public void delete(int bno)throws Exception;
}
