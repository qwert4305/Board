package com.spring.board.Service;

import com.spring.board.DTO.MemberDTO;

public interface MemberService {

	public void insertMember(MemberDTO dto) throws Exception;
	public MemberDTO selectLogin(MemberDTO dto) throws Exception;
	public boolean idcheck(String id) throws Exception;

	
}
