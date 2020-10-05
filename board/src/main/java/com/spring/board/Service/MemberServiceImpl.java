package com.spring.board.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.DAO.MemberDAO;
import com.spring.board.DTO.MemberDTO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO dao;
	
	@Override
	public void insertMember(MemberDTO dto) throws Exception {
		dao.insertMember(dto);
	}
	
	@Override
	public MemberDTO selectLogin(MemberDTO dto) throws Exception {
		return dao.selectLogin(dto);
	}
	
	@Override
	public boolean idcheck(String id) throws Exception {
		return dao.idcheck(id);
	}
}
