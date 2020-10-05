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
	
	//���� ���ε��� �̹��� �����Ͽ��� ����
	@RequestMapping("/imageView")
	public void imageView(@RequestParam("articleno")int articleno, @RequestParam("file")String file, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		
		OutputStream output = response.getOutputStream();
		
		String urlPath =PATH+"/"+articleno+"/"+file;
		
		URL url = new URL(urlPath);
		
		InputStream input = url.openStream();//
		FileCopyUtils.copy(input, output);
		
	}
	
	//���ε��� ���� �ٿ�ε�
	@RequestMapping("/download.do")
	public void imageDownload(@RequestParam("articleno")int articleno, @RequestParam("file")String file,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment;fileName=" +file);//file �̸� �����ؾ� �ٿ�ε� �� �� ����
		OutputStream out = response.getOutputStream(); //������ output��ü ����
		
		String urlPath = PATH+"/"+articleno+"/"+file; //������ ������ ���
		
		URL url = new URL(urlPath); //������ ������ ��η� url����
		
		InputStream in = url.openStream(); //������ url���� ���� �޾ƿ�
		FileCopyUtils.copy(in, out); //in���� �԰�, out���� ����
		
	}
}
