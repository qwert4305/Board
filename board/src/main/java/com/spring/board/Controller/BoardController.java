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

	private static String PATH = "C:\\boardimg"; // 서버에 이미지 저장될 경로는 숨기고 공유

	private static int startPage = 1;
	private static int currentPage = 1;
	private static int endPage = 1;
	
	@Autowired
	BoardService service;
	

	// 게시판 목록 보기
	@RequestMapping("/boardList")
	public ModelAndView boardList(@RequestParam(value="pagenum", defaultValue = "1") int pagenum, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("/board/boardList");

		endPage = service.selectPageCnt(); //마지막 페이지 설정 메소드 불러옴
		currentPage=pagenum;//누르는 페이지를 현재 페이지로 바꿈
		startPage = ((currentPage-1)/5)*5+1;
		session.setAttribute("pagenum", endPage); //endPage를 pagenum으로 세팅
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("startPage", startPage);
		
		
		List<BoardDTO> all = service.selectall(currentPage);
		mav.addObject("all", all);

		return mav;
	}

	// 게시판 글쓰기 버튼 누르면 글쓰기 양식으로 이동
	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public ModelAndView boardWriteView(BoardDTO dto) throws Exception { // JSP에서 보낸 데이터 DTO타입으로 받아 값까지 세팅
		ModelAndView mav = new ModelAndView("/board/boardWrite");

		return mav;
	}

	// 글 쓴 후 등록 버튼 누르면 목록에 추가
	@RequestMapping(value = "/boardWrite.do", method = RequestMethod.POST)
	public ModelAndView boardWrite(BoardDTO dto, @RequestParam("file") MultipartFile file) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/boardList");
		// System.out.println(file); jsp에서 가져온 파일 저장 번지수

		String originName = file.getOriginalFilename(); // 파일의 실제 이름 가져오기
		String dirLocation = PATH + "/" + service.selectArticleNo(); // 하위 폴더 생성

		try {
			File makeDir = new File(dirLocation); // 실제 폴더 생성 위해 파일 객체 생성
			if (!makeDir.exists()) { // 하위 폴더 생성
				try {
					makeDir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!file.isEmpty() && file.getSize() != 0) {
				try { // 파일 경로 생성 (기본 경로(c:\\boardimg)+하위폴더(articleno)+파일이름 및 실제 파일로 변경
					File filePath = new File(dirLocation + "/" + originName);
					file.transferTo(filePath); // 위 경로로 실제 파일로 저장
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		dto.setImagename(originName); // RequestParam으로 따로 받아와서 dto에 세팅 해줘야함
		service.insertall(dto); // 모든 정보 담아 insert(articleno포함)

		return mav;
	}

	// 글 제목 누르면 상세 페이지로 이동
	@RequestMapping(value = "/boardDetail.do", method = RequestMethod.GET)
	public ModelAndView boardDetail(BoardDTO dto) throws Exception {
		ModelAndView mav = new ModelAndView("/board/boardDetail");
		BoardDTO detailAll = service.selectDetail(dto.getArticleno());
		dto.setHit(service.selectViewNo(dto.getArticleno()));// jsp에서 받아온 articleno으로 viewNo 검색해서+1 올린 후 dto의 조회수 세팅
		service.updateViewNo(dto); // 위에서 세팅한 hit 으로 update

		mav.addObject("detailAll", detailAll);
		return mav;
	}

	// 글삭제
	@RequestMapping(value = "/boardDelete.do", method = RequestMethod.POST)
	public ModelAndView boardDelete(@RequestParam("articleno") int articleno) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/boardList");
		service.deleteall(articleno);

		return mav;
	}

	// 글수정뷰로 이동
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public ModelAndView boardUpdateView(@RequestParam("articleno") int articleno, @RequestParam("writedate") Date writedate) throws Exception {
		ModelAndView mav = new ModelAndView("/board/boardUpdate");

		mav.addObject("articleno", articleno);
		mav.addObject("writedate", writedate);
		return mav;
	}

	// 글수정 후 글목록으로 보냄
	@RequestMapping(value = "/boardUpdate.do", method = RequestMethod.POST)
	public ModelAndView boardUpdate(BoardDTO dto, @RequestParam("file") MultipartFile file2) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/boardList");
		// BoardDTO board = new BoardDTO();
		// dto.setArticleno(articleno); // dto에 articleno 세팅

		String getFileName = service.selectImageName(dto.getArticleno()); // 현재 올라간 이미지 파일의 이름
		String originName2 = file2.getOriginalFilename(); // 수정할 이미지 파일의 진짜 이름
		String dirLocation2 = PATH + "\\" + dto.getArticleno(); // 수정할 페이지의 articleno 을 겟해야함

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
		// System.out.println(file); jsp에서 가져온 파일 저장 번지수

		String originName = file.getOriginalFilename(); // 파일의 실제 이름 가져오기
		String dirLocation = PATH + "/" + service.selectArticleNo(); // 하위 폴더 생성

		try {
			File makeDir = new File(dirLocation); // 실제 폴더 생성 위해 파일 객체 생성
			if (!makeDir.exists()) { // 하위 폴더 생성
				try {
					makeDir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!file.isEmpty() && file.getSize() != 0) {
				try { // 파일 경로 생성 (기본 경로(c:\\boardimg)+하위폴더(articleno)+파일이름 및 실제 파일로 변경
					File filePath = new File(dirLocation + "/" + originName);
					file.transferTo(filePath); // 위 경로로 실제 파일로 저장
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		dto.setImagename(originName); // RequestParam으로 따로 받아와서 dto에 세팅 해줘야함
		service.insertall(dto);
		
		return mav;
	}

}
