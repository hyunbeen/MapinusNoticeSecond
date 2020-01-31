package org.zerock.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.ReplyDAO;


@Controller
@RequestMapping("/replies")
public class ReplyController {

	@Inject
	private ReplyDAO rdao;
	
	@Autowired
	@Qualifier("vo")
	private ReplyVO vo;
	
	@Autowired
	@Qualifier("newVO")
	private ReplyVO newVO;
	
	@Autowired
	@Qualifier("selectVO")
	private ReplyVO selectVO;

	//리뷰 추가 페이지 
	@RequestMapping(value = "/replyInsertPage", method = RequestMethod.GET)
	public void reviewInsertPage(@RequestParam("n_num") int n_num,Model model) throws Exception {
		model.addAttribute("n_num",n_num);
	}

	//리뷰 수정하기페이지로 이동하기
	@RequestMapping(value = "/replyModifyPage", method = RequestMethod.GET)
	public void reviewModifyPage(@RequestParam("r_num") int r_num,@RequestParam("n_num") int n_num , @RequestParam("r_user") String r_user,@RequestParam("r_content") String r_content , Model model) throws Exception {
		model.addAttribute("r_num", r_num);
		model.addAttribute("n_num", n_num);
		model.addAttribute("r_user", r_user);
		model.addAttribute("r_content", r_content);
	}

	//리뷰에 리뷰 달기 페이지 
	@RequestMapping(value = "/replyGroupPage", method = RequestMethod.GET)
	public void modify(@RequestParam("r_num") int r_num , @RequestParam("n_num") String n_num , Model model) throws Exception {
		model.addAttribute("r_num",r_num);
		model.addAttribute("n_num",n_num);
	}

	//창닫기 순서때문에
	@RequestMapping(value = "/replyClose", method = RequestMethod.POST)
	@ResponseBody
	public void replyClose() throws Exception {

	}

	//댓글 등록
	@RequestMapping(value = "/replyInsert", method = RequestMethod.POST)
	public void replyInsert(ReplyVO replyVO,@RequestParam("n_num") int n_num) throws Exception {
		rdao.replyCreate(replyVO,n_num);
	}
	//댓글 삭제
	@RequestMapping(value = "/replyDelete", method = RequestMethod.POST)
	@ResponseBody
	public void replyDelete(@RequestParam("r_num") int r_num) throws Exception {
		rdao.replyDelete(r_num);
	}
	//댓글 수정
	@RequestMapping(value = "/replyModify", method = RequestMethod.POST)
	@ResponseBody
	public void replyModify(@RequestParam("r_num") int r_num , @RequestParam("r_content") String r_content) throws Exception {


		vo.setR_num(r_num);
		vo.setR_content(r_content);
		rdao.replyUpdate(vo);

	}
	//댓글에 댓글 추가
	@RequestMapping(value = "/replyGroup", method = RequestMethod.POST)
	@ResponseBody
	public void replyGroup(@RequestParam("r_num") int r_num , @RequestParam("r_content") String r_content,@RequestParam("r_user") String r_user,@RequestParam("n_num") String n_num) throws Exception {

		selectVO.setR_num(r_num); 

		int r_child = rdao.replyChild(selectVO); //부모의 리플개수를 업데이트하고 가져온다 
		newVO = rdao.replyFindOne(selectVO);//선택한 글의 정보를 가져온다
		int increase = (int)Math.pow(10,7-newVO.getR_order());



		newVO.setR_group(newVO.getR_group()+r_child * increase);
		newVO.setR_order(newVO.getR_order()+1);//레벨을 바꾼다
		newVO.setR_user(r_user);
		newVO.setR_content(r_content);//댓글의 정보를 저장한다


		rdao.replyCreateGroup(newVO);
	}



}
