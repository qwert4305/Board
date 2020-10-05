package com.spring.board.Controller;

import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.DTO.BoardDTO;
import com.spring.board.Service.BoardService;

@Controller
public class BoardController {

	private static String PATH = "C:\\boardimg"; // ������ �̹��� ����� ��δ� ����� ����

	private static int startPage = 1;
	private static int currentPage = 1;
	private static int endPage = 1;
	
	@Autowired
	BoardService service;
	

	// �Խ��� ��� ����
	@RequestMapping("/boardList")
	public ModelAndView boardList(@RequestParam(value="pagenum", defaultValue = "1") int pagenum, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("/board/boardList");

		endPage = service.selectPageCnt(); //������ ������ ���� �޼ҵ� �ҷ���
		currentPage=pagenum;//������ �������� ���� �������� �ٲ�
		startPage = ((currentPage-1)/5)*5+1;
		session.setAttribute("pagenum", endPage); //endPage�� pagenum���� ����
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("startPage", startPage);
		
		
		List<BoardDTO> all = service.selectall(currentPage);
		mav.addObject("all", all);

		return mav;
	}

	// �Խ��� �۾��� ��ư ������ �۾��� ������� �̵�
	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public ModelAndView boardWriteView(BoardDTO dto) throws Exception { // JSP���� ���� ������ DTOŸ������ �޾� ������ ����
		ModelAndView mav = new ModelAndView("/board/boardWrite");

		return mav;
	}

	// �� �� �� ��� ��ư ������ ��Ͽ� �߰�
	@RequestMapping(value = "/boardWrite.do", method = RequestMethod.POST)
	public ModelAndView boardWrite(BoardDTO dto, @RequestParam("file") MultipartFile file) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/boardList");
		// System.out.println(file); jsp���� ������ ���� ���� ������

		String originName = file.getOriginalFilename(); // ������ ���� �̸� ��������
		String dirLocation = PATH + "/" + service.selectArticleNo(); // ���� ���� ����

		try {
			File makeDir = new File(dirLocation); // ���� ���� ���� ���� ���� ��ü ����
			if (!makeDir.exists()) { // ���� ���� ����
				try {
					makeDir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!file.isEmpty() && file.getSize() != 0) {
				try { // ���� ��� ���� (�⺻ ���(c:\\boardimg)+��������(articleno)+�����̸� �� ���� ���Ϸ� ����
					File filePath = new File(dirLocation + "/" + originName);
					file.transferTo(filePath); // �� ��η� ���� ���Ϸ� ����
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		dto.setImagename(originName); // RequestParam���� ���� �޾ƿͼ� dto�� ���� �������
		service.insertall(dto); // ��� ���� ��� insert(articleno����)

		return mav;
	}

	// �� ���� ������ �� �������� �̵�
	@RequestMapping(value = "/boardDetail.do", method = RequestMethod.GET)
	public ModelAndView boardDetail(BoardDTO dto) throws Exception {
		ModelAndView mav = new ModelAndView("/board/boardDetail");
		BoardDTO detailAll = service.selectDetail(dto.getArticleno());
		dto.setHit(service.selectViewNo(dto.getArticleno()));// jsp���� �޾ƿ� articleno���� viewNo �˻��ؼ�+1 �ø� �� dto�� ��ȸ�� ����
		service.updateViewNo(dto); // ������ ������ hit ���� update

		mav.addObject("detailAll", detailAll);
		return mav;
	}

	// �ۻ���
	@RequestMapping(value = "/boardDelete.do", method = RequestMethod.POST)
	public ModelAndView boardDelete(@RequestParam("articleno") int articleno) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/boardList");
		service.deleteall(articleno);

		return mav;
	}

	// �ۼ������ �̵�
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public ModelAndView boardUpdateView(@RequestParam("articleno") int articleno, @RequestParam("writedate") Date writedate) throws Exception {
		ModelAndView mav = new ModelAndView("/board/boardUpdate");

		mav.addObject("articleno", articleno);
		mav.addObject("writedate", writedate);
		return mav;
	}

	// �ۼ��� �� �۸������ ����
	@RequestMapping(value = "/boardUpdate.do", method = RequestMethod.POST)
	public ModelAndView boardUpdate(BoardDTO dto, @RequestParam("file") MultipartFile file2) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/boardList");
		// BoardDTO board = new BoardDTO();
		// dto.setArticleno(articleno); // dto�� articleno ����

		String getFileName = service.selectImageName(dto.getArticleno()); // ���� �ö� �̹��� ������ �̸�
		String originName2 = file2.getOriginalFilename(); // ������ �̹��� ������ ��¥ �̸�
		String dirLocation2 = PATH + "\\" + dto.getArticleno(); // ������ �������� articleno �� ���ؾ���

		try {
			if (!originName2.equals(getFileName)) {
				try {
					File deletePath = new File(dirLocation2 + "\\" + getFileName);
					if (deletePath.isFile()) {
						deletePath.delete();
					} 
					if (!file2.isEmpty() && file2.getSize() != 0) {

						File filePath2 = new File(dirLocation2 + "\\" + originName2);
						file2.transferTo(filePath2);

					 }

				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		dto.setImagename(originName2);
		service.updateall(dto);
		return mav;
	}
	
	@RequestMapping("/boardReply")
	public ModelAndView boardReplyView(@RequestParam("parentno")int parentno) {
		ModelAndView mav = new ModelAndView("/board/boardReply");
		mav.addObject("parentno", parentno);
		
		return mav;
	}
	
	@RequestMapping(value="/boardReply.do", method=RequestMethod.POST)
	public ModelAndView boardReply(BoardDTO dto, @RequestParam("file") MultipartFile file) throws Exception {
		
		ModelAndView mav = new ModelAndView("redirect:/boardList");
		// System.out.println(file); jsp���� ������ ���� ���� ������

		String originName = file.getOriginalFilename(); // ������ ���� �̸� ��������
		String dirLocation = PATH + "/" + service.selectArticleNo(); // ���� ���� ����

		try {
			File makeDir = new File(dirLocation); // ���� ���� ���� ���� ���� ��ü ����
			if (!makeDir.exists()) { // ���� ���� ����
				try {
					makeDir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!file.isEmpty() && file.getSize() != 0) {
				try { // ���� ��� ���� (�⺻ ���(c:\\boardimg)+��������(articleno)+�����̸� �� ���� ���Ϸ� ����
					File filePath = new File(dirLocation + "/" + originName);
					file.transferTo(filePath); // �� ��η� ���� ���Ϸ� ����
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		dto.setImagename(originName); // RequestParam���� ���� �޾ƿͼ� dto�� ���� �������
		service.insertall(dto);
		
		return mav;
	}

}
