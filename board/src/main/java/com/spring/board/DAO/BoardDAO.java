package com.spring.board.DAO;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.board.DTO.BoardDTO;

public interface BoardDAO {

	public List<BoardDTO> selectall(int pagenum) throws DataAccessException;
    public int selectPageCnt() throws DataAccessException;
	public void insertall(BoardDTO dto) throws DataAccessException;
    public BoardDTO selectDetail(int articleno) throws DataAccessException;
    public void deleteall(int articleno) throws DataAccessException;
    public void updateall(BoardDTO dto) throws DataAccessException;
    public int selectArticleNo()throws DataAccessException;
    public int selectViewNo(int articleno) throws DataAccessException;
    public void updateViewNo(BoardDTO dto) throws DataAccessException;
    public String selectImageName(int articleno) throws DataAccessException;
   
}
