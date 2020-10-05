package com.spring.board.DTO;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BoardDTO {

	private int articleno;
	private int parentno;
	private Date writedate;
	private int hit;
	private String writer;
	private String title;
	private String content;
	private String imagename;
	private int layer;
	
	
	
}
