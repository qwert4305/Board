package com.spring.board.DTO;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MemberDTO {

	int code;
	String id; 
	String pw;
	String name;
    String phone1; 
	String phone2;
	String phone3;
	String tel1;
	String tel2;
	String tel3;
	Date joindate;
	String email1;
	String email2;
	
}
