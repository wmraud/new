package kr.com.web.board;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.com.web.Criteria;
import kr.com.web.member.MemberVO;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Inject
	private SqlSession sqlSession;
	
	// �Խñ� �ۼ�
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sqlSession.insert("boardMapper.write", boardVO);
	}
	
	// �Խù� ��� ��ȸ
	@Override
	public List<BoardVO> list(Criteria cri) throws Exception {
		return sqlSession.selectList("boardMapper.listPage" , cri);
    }
	
	// �Խù� �� ����
	@Override
	public int listCount() throws Exception {
		return sqlSession.selectOne("boardMapper.listCount");
    }
	
	// �Խ��� �� ��ȸ
	@Override
	public BoardVO read(int bno) throws Exception{
		return sqlSession.selectOne("boardMapper.read", bno);
	}
	
	//�Խù� ����
	@Override
	public void update(BoardVO boardVO) throws Exception {
		sqlSession.update("boardMapper.update", boardVO);
	}
	
	//�Խù� ����
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("boardMapper.delete", bno);
	}

}