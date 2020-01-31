package org.zerock.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.persistence.BoardDAO;
import org.zerock.persistence.FileDAO;
import org.zerock.persistence.FileDAO;
import org.zerock.persistence.ReplyDAO;
import org.zerock.service.PagingService;




@Controller
@RequestMapping("/sboard")
public class SearchBoardController {


	@Inject
	private PagingService pservice;


	@Inject
	private BoardDAO bdao;


	@Inject
	private ReplyDAO rdao;


	@Inject
	private FileDAO fdao;

	//��� ������ �̵�
	@RequestMapping(value = "/boardInsertPage")
	public void boardInsertPage() throws Exception {

	}




	//�Խ��� ��ü �� ��������
	@RequestMapping(value = "/boardListPage", method = RequestMethod.GET)
	public void boardListPage(Model model,@RequestParam("page_str") String page_str,HttpSession session ,HttpServletRequest req  ) throws Exception {



		//����� ���ǿ� ���� ����
		int page_iterative = 10; //�������� ���ǰ��� 
		int page_screen = 5; //ȭ��� ����¡��  �ִ� ����
		int page_count = 0;//�������� ����¡�� ���� 
		int data_start = 1;
		int size = bdao.boardListSize().size();
		int last_page_num = ((size - 1) / page_iterative) + 1; //������ ������ ���� ������ �������� ���� ���
		int page_num = Integer.parseInt(page_str);//���� �������� ����
		int exbtn = 0;
		int nebtn = 0;
		int page_start = 0; //�������� ���� �� ��ȣ

		Map<String,Integer> page = pservice.calculatePage(page_num , last_page_num,size,page_iterative,page_screen);


		model.addAttribute("list", bdao.boardListPart(page.get("data_start"),page_iterative));//�Խ��Ǹ���Ʈ
		model.addAttribute("exbtn",page.get("exbtn"));//������ư ��������
		model.addAttribute("nebtn",page.get("nebtn"));//������ư ��������
		model.addAttribute("page_num",page.get("page_num"));//���� ������
		model.addAttribute("page_screen",page_screen);//��ȭ���� ����¡ �ִ� ����
		model.addAttribute("page_count", page.get("page_count"));//��ȭ�� ����¡ ����
		model.addAttribute("page_start", page.get("page_start"));//��ȭ���� ����¡ ���� ��ȣ



	}

	//���������� �̵�
	@RequestMapping(value = "/boardReadPage", method = RequestMethod.GET)
	public void boardReadPage(@RequestParam("n_num") int n_num,@RequestParam("re_page_str") String re_page_str, Model model,HttpSession session)
			throws Exception {

		//����� ���ǿ� ���� ����
		int re_page_iterative = 10; //�������� ���ǰ��� 
		int re_page_screen = 5; //ȭ��� ����¡��  �ִ� ����
		int re_size = rdao.replyListSize(n_num).size();
		int re_page_count = 0;//�������� ����¡�� ���� 
		int re_data_start = 1;
		int re_last_page_num = ((re_size - 1) / re_page_iterative) + 1; //������ ������ ���� ������ �������� ���� ���
		int re_page_num = Integer.parseInt(re_page_str);//���� �������� ����
		int re_exbtn = 0;
		int re_nebtn = 0;
		int re_page_start = 0; //�������� ���� �� ��ȣ

		Map<String,Integer> re_page = pservice.calculatePage(re_page_num , re_last_page_num,re_size,re_page_iterative,re_page_screen);

		bdao.boardUpdateCount(n_num); //�Խ��� �� ��������

		model.addAttribute("n_num",n_num); //�Խ��� ��ȣ �Ķ���� ����	
		model.addAttribute("boardVO",bdao.boardRead(n_num)); //�Խ��� �� ��������
		model.addAttribute("replyList",rdao.replyListPart(n_num,re_page.get("data_start"),re_page_iterative)); //�ش� ���� ���� ��������
		model.addAttribute("fileList",fdao.listFileAll(n_num)); //�ش� ���� ���� ��������

		model.addAttribute("re_exbtn",re_page.get("exbtn"));//����� ����¡ ������ư ��������
		model.addAttribute("re_nebtn",re_page.get("nebtn"));//����� ����¡ ������ư ��������
		model.addAttribute("re_page_num",re_page.get("page_num"));//���� ����� ������
		model.addAttribute("re_page_screen",re_page_screen);//��ȭ���� ��� ����¡ �ִ밳��
		model.addAttribute("re_page_count", re_page.get("page_count"));//��ȭ��  ��� ����¡ ����
		model.addAttribute("re_page_start", re_page.get("page_start"));//��ȭ�� ��� ����¡ ���� ��ȣ

	}

	//�˻�
	@RequestMapping(value = "/boardSearchPage", method = RequestMethod.GET)
	public void boardSearchPage(@RequestParam("item") String item , @RequestParam("word") String word, @RequestParam("search_page_str") String search_page_str,Model model,HttpSession session  ) throws Exception {

		//����� ���ǿ� ���� ����
		int search_page_iterative = 10;
		int search_page_screen = 5; //ȭ��� ����¡��  �ִ� ����
		String cond = "n_user"; //db���� �˻� �÷� ��
		//db�÷��� ã��
		if(item.equals("�ۼ���")) {
			cond = "n_user";
		}else if(item.equals("������")) {
			cond = "n_title";
		}

		int search_size = bdao.boardSearchListSize(cond,word).size();
		int search_page_count = 0;//�������� ����¡�� ���� 
		int search_data_start = 1;
		int search_last_page_num = ((search_size - 1) / search_page_iterative) + 1; //������ ������ ���� ������ �������� ���� ���
		int search_page_num = Integer.parseInt(search_page_str);//���� �������� ����
		int search_exbtn = 0;
		int search_nebtn = 0;
		int search_page_start = 0; //�������� ���� �� ��ȣ

		Map<String,Integer> search_page = pservice.calculatePage(search_page_num , search_last_page_num,search_size,search_page_iterative,search_page_screen);
	
		//�˻��� ����Ʈ�� �˻��ܾ� ������ ����
		model.addAttribute("searchlist", bdao.boardSearchListPart(cond,word,search_page.get("data_start"),search_page_iterative));
		model.addAttribute("item", item);
		model.addAttribute("word", word);

		model.addAttribute("search_exbtn",search_page.get("exbtn"));//�˻��������� ����¡ ������ư ��������
		model.addAttribute("search_nebtn",search_page.get("nebtn"));//�˻��������� ����¡ ������ư ��������
		model.addAttribute("search_page_num",search_page.get("page_num"));//���� �˻��������� ������
		model.addAttribute("search_page_screen",search_page_screen);//��ȭ���� �˻������� ����¡ �ִ밳��
		model.addAttribute("search_page_count", search_page.get("page_count"));//��ȭ��  �˻������� ����¡ ����
		model.addAttribute("search_page_start", search_page.get("page_start"));//��ȭ�� �˻������� ����¡ ���� ��ȣ

	}



	//���� �������� �̵�
	@RequestMapping(value = "/boardModifyPage")
	public void boardModifyPage(@RequestParam("n_num") int n_num,Model model) throws Exception{

		model.addAttribute("fileList",fdao.listFileAll(n_num));
		model.addAttribute("boardVO",bdao.boardRead(n_num));

	}

	//â�ݱ� ����������
	@RequestMapping(value = "/boardClose", method = RequestMethod.POST)
	@ResponseBody
	public void boardClose() throws Exception {

	}

	//���� ����
	@RequestMapping(value = "/boardDelete", method = RequestMethod.POST)
	@ResponseBody
	public String boardDelete(@RequestParam("n_num") int n_num, RedirectAttributes rttr) throws Exception {

		bdao.boardDelete(n_num);
		return "redirect:/sboard/boardListPage?page_str=1";
	}


	//���� ����
	@RequestMapping(value = "/boardModify", method = RequestMethod.POST)
	public void boardModify(BoardVO vo) throws Exception {

		bdao.boardUpdate(vo);

	}


	//���� �߰�
	@RequestMapping(value = "/boardInsert", method = RequestMethod.POST)
	public String boardInsert(BoardVO BoardVO) throws Exception {


		bdao.boardInsert(BoardVO);
		return "redirect:/sboard/boardListPage?page_str=1";
	}

	//���� ��ȣ ã��
	@RequestMapping(value = "/boardFindNum", method = RequestMethod.POST)
	@ResponseBody
	public Integer boardFindNum() throws Exception {


		return bdao.boardFindNum();    
	}






}



