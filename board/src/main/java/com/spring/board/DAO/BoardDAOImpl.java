package com.spring.board.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.board.DTO.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession session;

	@Override
	public List<BoardDTO> selectall(int pagenum) throws DataAccessException {

		return session.selectList("mappers.board.selectall", pagenum);
	}

	@Override
	public int selectPageCnt() throws DataAccessException {

		return session.selectOne("mappers.board.selectPageCnt");
	}

	@Override
	public void insertall(BoardDTO dto) throws DataAccessException {

		session.insert("mappers.board.insertall", dto);
	}

	@Override
	public BoardDTO selectDetail(int articleno) throws DataAccessException {
		return session.selectOne("mappers.board.selectDetail", articleno);
	}

	@Override
	public void deleteall(int articleno) throws DataAccessException {
		session.delete("mappers.board.deleteall", articleno);
	}

	@Override
	public void updateall(BoardDTO dto) throws DataAccessException {
		session.update("mappers.board.updateall", dto);

	}

	@Override
	public int selectArticleNo() throws DataAccessException {
		return session.selectOne("mappers.board.selectArticleNo");
	}

	@Override
	public int selectViewNo(int articleno) throws DataAccessException {
		return session.selectOne("mappers.board.selectViewNo", articleno);
	}

	@Override
	public void updateViewNo(BoardDTO dto) throws DataAccessException {
		session.update("mappers.board.updateViewNo", dto);
	}

	@Override
	public String selectImageName(int articleno) throws DataAccessException {
		return session.selectOne("mappers.board.selectImageName", articleno);
	}

}
