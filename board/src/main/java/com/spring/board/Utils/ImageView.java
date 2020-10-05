package com.spring.board.Utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImageView {

	private static String PATH="file:///C:/boardimg";
	
	//파일 업로드한 이미지 디테일에서 보기
	@RequestMapping("/imageView")
	public void imageView(@RequestParam("articleno")int articleno, @RequestParam("file")String file, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		
		OutputStream output = response.getOutputStream();
		
		String urlPath =PATH+"/"+articleno+"/"+file;
		
		URL url = new URL(urlPath);
		
		InputStream input = url.openStream();//
		FileCopyUtils.copy(input, output);
		
	}
	
	//업로드한 파일 다운로드
	@RequestMapping("/download.do")
	public void imageDownload(@RequestParam("articleno")int articleno, @RequestParam("file")String file,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment;fileName=" +file);//file 이름 세팅해야 다운로드 할 수 있음
		OutputStream out = response.getOutputStream(); //보내는 output객체 생성
		
		String urlPath = PATH+"/"+articleno+"/"+file; //가져올 파일의 경로
		
		URL url = new URL(urlPath); //가져올 파일의 경로로 url생성
		
		InputStream in = url.openStream(); //생성된 url에서 파일 받아옴
		FileCopyUtils.copy(in, out); //in에서 먹고, out으로 뱉음
		
	}
}
