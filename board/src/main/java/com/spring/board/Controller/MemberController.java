package com.spring.board.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.DTO.MemberDTO;
import com.spring.board.Service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	@RequestMapping("/memberJoin")
	public ModelAndView memberJoinView() throws Exception {
		ModelAndView mav = new ModelAndView("/member/memberJoin");

		return mav;
	}

	@RequestMapping(value = "/memberJoin.do", method = RequestMethod.POST)
	public ModelAndView memberJoin(MemberDTO dto) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/boardList");
		service.insertMember(dto);
		return mav;
	}

	@RequestMapping("/memberLogin")
	public ModelAndView memberLoginView() {
		ModelAndView mav = new ModelAndView("/member/memberLogin");
		return mav;
	}

	@RequestMapping(value = "/memberLogin.do", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	@ResponseBody // string Ÿ������ AJAX�� ����
	public String memberLogin(MemberDTO dto, HttpSession session) throws Exception {

		MemberDTO memberInfo = service.selectLogin(dto);

//		// �α��� ���� ���� �Ʒ� if ���� ��ħ
//		if (memberInfo != null || memberInfo.getId() != null) {
//			session.setAttribute("isLogOn", true);
//			session.setAttribute("memberInfo", memberInfo);
//		}

		if (memberInfo != null) {
			// �α��� ���� ����
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo", memberInfo);
			// ajax�� true ����
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping("/memberLogout.do")
	public ModelAndView memberLogout(HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/boardList");
		session.removeAttribute("isLogOn");
		session.removeAttribute("memberInfo");
		return mav;
	}

	@RequestMapping(value = "/idcheck", method = RequestMethod.POST, produces = "application/text; charset=utf-8")//���� ���������� utf-8�� ����
	@ResponseBody
	public String idcheck(String id) throws Exception {
		if (service.idcheck(id)) { //idcheck�� �޼ҵ尡 true��
			return "�ߺ��� ���̵��Դϴ�.";
		} else { //false�� 
			return "��� ������ ���̵��Դϴ�.";
		}

	}

}
