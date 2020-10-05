package com.spring.board.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.DAO.BoardDAO;
import com.spring.board.DTO.BoardDTO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO dao;

	@Override
	public List<BoardDTO> selectall(int pagenum) throws Exception {

		return dao.selectall(pagenum);
	}
	
	@Override
	public int selectPageCnt() throws Exception {
		
		return dao.selectPageCnt();
	}

	@Override
	public void insertall(BoardDTO dto) throws Exception {
		dao.insertall(dto);
	}

	@Override
	public BoardDTO selectDetail(int articleno) throws Exception {
		return dao.selectDetail(articleno);
	}

	@Override
	public void deleteall(int articleno) throws Exception {
		dao.deleteall(articleno);
	}

	@Override
	public void updateall(BoardDTO dto) throws Exception {
		dao.updateall(dto);

	}

	@Override
	public int selectArticleNo() throws Exception {
		return dao.selectArticleNo();
	}

	@Override
	public int selectViewNo(int articleno) throws Exception {
		return dao.selectViewNo(articleno);
	}

	@Override
	public void updateViewNo(BoardDTO dto) throws Exception {
		dao.updateViewNo(dto);
	}

	@Override
	public String selectImageName(int articleno) throws Exception {
		return dao.selectImageName(articleno);
	}

}
