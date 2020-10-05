package com.spring.board.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.board.DTO.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	SqlSession session;

	@Override
	public void insertMember(MemberDTO dto) throws DataAccessException {
		session.selectOne("mappers.member.insertMember", dto);
	}

	@Override
	public MemberDTO selectLogin(MemberDTO dto) throws DataAccessException {
		return session.selectOne("mappers.member.selectLogin", dto);
	}
	
	@Override
	public boolean idcheck(String id) throws DataAccessException {
		
		return session.selectOne("mappers.member.idcheck", id);
	}

}
