package com.spring.board.Service;

import java.util.List;

import com.spring.board.DTO.BoardDTO;


public interface BoardService {

	public List<BoardDTO> selectall(int pagenum) throws Exception;
	public int selectPageCnt() throws Exception;
	public void insertall(BoardDTO dto) throws Exception;
	public BoardDTO selectDetail(int articleno) throws Exception;
	public void deleteall(int articleno) throws Exception;
    public void updateall(BoardDTO dto) throws Exception;
    public int selectArticleNo() throws Exception;
    public int selectViewNo(int articleno) throws Exception;
    public void updateViewNo(BoardDTO dto) throws Exception;
    public String selectImageName(int articleno) throws Exception;
   
}
