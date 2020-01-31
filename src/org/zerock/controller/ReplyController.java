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

	//���� �߰� ������ 
	@RequestMapping(value = "/replyInsertPage", method = RequestMethod.GET)
	public void reviewInsertPage(@RequestParam("n_num") int n_num,Model model) throws Exception {
		model.addAttribute("n_num",n_num);
	}

	//���� �����ϱ��������� �̵��ϱ�
	@RequestMapping(value = "/replyModifyPage", method = RequestMethod.GET)
	public void reviewModifyPage(@RequestParam("r_num") int r_num,@RequestParam("n_num") int n_num , @RequestParam("r_user") String r_user,@RequestParam("r_content") String r_content , Model model) throws Exception {
		model.addAttribute("r_num", r_num);
		model.addAttribute("n_num", n_num);
		model.addAttribute("r_user", r_user);
		model.addAttribute("r_content", r_content);
	}

	//���信 ���� �ޱ� ������ 
	@RequestMapping(value = "/replyGroupPage", method = RequestMethod.GET)
	public void modify(@RequestParam("r_num") int r_num , @RequestParam("n_num") String n_num , Model model) throws Exception {
		model.addAttribute("r_num",r_num);
		model.addAttribute("n_num",n_num);
	}

	//â�ݱ� ����������
	@RequestMapping(value = "/replyClose", method = RequestMethod.POST)
	@ResponseBody
	public void replyClose() throws Exception {

	}

	//��� ���
	@RequestMapping(value = "/replyInsert", method = RequestMethod.POST)
	public void replyInsert(ReplyVO replyVO,@RequestParam("n_num") int n_num) throws Exception {
		rdao.replyCreate(replyVO,n_num);
	}
	//��� ����
	@RequestMapping(value = "/replyDelete", method = RequestMethod.POST)
	@ResponseBody
	public void replyDelete(@RequestParam("r_num") int r_num) throws Exception {
		rdao.replyDelete(r_num);
	}
	//��� ����
	@RequestMapping(value = "/replyModify", method = RequestMethod.POST)
	@ResponseBody
	public void replyModify(@RequestParam("r_num") int r_num , @RequestParam("r_content") String r_content) throws Exception {


		vo.setR_num(r_num);
		vo.setR_content(r_content);
		rdao.replyUpdate(vo);

	}
	//��ۿ� ��� �߰�
	@RequestMapping(value = "/replyGroup", method = RequestMethod.POST)
	@ResponseBody
	public void replyGroup(@RequestParam("r_num") int r_num , @RequestParam("r_content") String r_content,@RequestParam("r_user") String r_user,@RequestParam("n_num") String n_num) throws Exception {

		selectVO.setR_num(r_num); 

		int r_child = rdao.replyChild(selectVO); //�θ��� ���ð����� ������Ʈ�ϰ� �����´� 
		newVO = rdao.replyFindOne(selectVO);//������ ���� ������ �����´�
		int increase = (int)Math.pow(10,7-newVO.getR_order());



		newVO.setR_group(newVO.getR_group()+r_child * increase);
		newVO.setR_order(newVO.getR_order()+1);//������ �ٲ۴�
		newVO.setR_user(r_user);
		newVO.setR_content(r_content);//����� ������ �����Ѵ�


		rdao.replyCreateGroup(newVO);
	}



}
