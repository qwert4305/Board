package com.spring.board.DAO;

import org.springframework.dao.DataAccessException;

import com.spring.board.DTO.MemberDTO;

public interface MemberDAO {

	public void insertMember(MemberDTO dto)throws DataAccessException;
	public MemberDTO selectLogin(MemberDTO dto)throws DataAccessException;
	public boolean idcheck(String id) throws DataAccessException;
}
