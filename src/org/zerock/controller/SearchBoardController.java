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

	//등록 페이지 이동
	@RequestMapping(value = "/boardInsertPage")
	public void boardInsertPage() throws Exception {

	}




	//게시판 전체 글 가져오기
	@RequestMapping(value = "/boardListPage", method = RequestMethod.GET)
	public void boardListPage(Model model,@RequestParam("page_str") String page_str,HttpSession session ,HttpServletRequest req  ) throws Exception {



		//사용자 편의에 따라 수정
		int page_iterative = 10; //페이지당 글의개수 
		int page_screen = 5; //화면당 페이징의  최대 개수
		int page_count = 0;//페이지당 페이징의 개수 
		int data_start = 1;
		int size = bdao.boardListSize().size();
		int last_page_num = ((size - 1) / page_iterative) + 1; //데이터 개수를 통한 마지막 페이지의 개수 계산
		int page_num = Integer.parseInt(page_str);//누른 페이지의 숫자
		int exbtn = 0;
		int nebtn = 0;
		int page_start = 0; //페이지당 시작 글 번호

		Map<String,Integer> page = pservice.calculatePage(page_num , last_page_num,size,page_iterative,page_screen);


		model.addAttribute("list", bdao.boardListPart(page.get("data_start"),page_iterative));//게시판리스트
		model.addAttribute("exbtn",page.get("exbtn"));//이전버튼 생성여부
		model.addAttribute("nebtn",page.get("nebtn"));//다음버튼 생성여부
		model.addAttribute("page_num",page.get("page_num"));//현재 페이지
		model.addAttribute("page_screen",page_screen);//한화면의 페이징 최대 개수
		model.addAttribute("page_count", page.get("page_count"));//한화면 페이징 개수
		model.addAttribute("page_start", page.get("page_start"));//한화면의 페이징 시작 번호



	}

	//상세페이지로 이동
	@RequestMapping(value = "/boardReadPage", method = RequestMethod.GET)
	public void boardReadPage(@RequestParam("n_num") int n_num,@RequestParam("re_page_str") String re_page_str, Model model,HttpSession session)
			throws Exception {

		//사용자 편의에 따라 수정
		int re_page_iterative = 10; //페이지당 글의개수 
		int re_page_screen = 5; //화면당 페이징의  최대 개수
		int re_size = rdao.replyListSize(n_num).size();
		int re_page_count = 0;//페이지당 페이징의 개수 
		int re_data_start = 1;
		int re_last_page_num = ((re_size - 1) / re_page_iterative) + 1; //데이터 개수를 통한 마지막 페이지의 개수 계산
		int re_page_num = Integer.parseInt(re_page_str);//누른 페이지의 숫자
		int re_exbtn = 0;
		int re_nebtn = 0;
		int re_page_start = 0; //페이지당 시작 글 번호

		Map<String,Integer> re_page = pservice.calculatePage(re_page_num , re_last_page_num,re_size,re_page_iterative,re_page_screen);

		bdao.boardUpdateCount(n_num); //게시판 글 가져오기

		model.addAttribute("n_num",n_num); //게시판 번호 파라미터 전달	
		model.addAttribute("boardVO",bdao.boardRead(n_num)); //게시판 글 가져오기
		model.addAttribute("replyList",rdao.replyListPart(n_num,re_page.get("data_start"),re_page_iterative)); //해당 글의 리플 가져오기
		model.addAttribute("fileList",fdao.listFileAll(n_num)); //해당 글의 리플 가져오기

		model.addAttribute("re_exbtn",re_page.get("exbtn"));//댓글의 페이징 이전버튼 생성여부
		model.addAttribute("re_nebtn",re_page.get("nebtn"));//댓글의 페이징 다음버튼 생성여부
		model.addAttribute("re_page_num",re_page.get("page_num"));//현재 댓글의 페이지
		model.addAttribute("re_page_screen",re_page_screen);//한화면의 댓글 페이징 최대개수
		model.addAttribute("re_page_count", re_page.get("page_count"));//한화면  댓글 페이징 개수
		model.addAttribute("re_page_start", re_page.get("page_start"));//한화면 댓글 페이징 시작 번호

	}

	//검색
	@RequestMapping(value = "/boardSearchPage", method = RequestMethod.GET)
	public void boardSearchPage(@RequestParam("item") String item , @RequestParam("word") String word, @RequestParam("search_page_str") String search_page_str,Model model,HttpSession session  ) throws Exception {

		//사용자 편의에 따라 수정
		int search_page_iterative = 10;
		int search_page_screen = 5; //화면당 페이징의  최대 개수
		String cond = "n_user"; //db에서 검색 컬럼 명
		//db컬럼명 찾기
		if(item.equals("작성자")) {
			cond = "n_user";
		}else if(item.equals("글제목")) {
			cond = "n_title";
		}

		int search_size = bdao.boardSearchListSize(cond,word).size();
		int search_page_count = 0;//페이지당 페이징의 개수 
		int search_data_start = 1;
		int search_last_page_num = ((search_size - 1) / search_page_iterative) + 1; //데이터 개수를 통한 마지막 페이지의 개수 계산
		int search_page_num = Integer.parseInt(search_page_str);//누른 페이지의 숫자
		int search_exbtn = 0;
		int search_nebtn = 0;
		int search_page_start = 0; //페이지당 시작 글 번호

		Map<String,Integer> search_page = pservice.calculatePage(search_page_num , search_last_page_num,search_size,search_page_iterative,search_page_screen);
	
		//검색된 리스트랑 검색단어 조건을 저장
		model.addAttribute("searchlist", bdao.boardSearchListPart(cond,word,search_page.get("data_start"),search_page_iterative));
		model.addAttribute("item", item);
		model.addAttribute("word", word);

		model.addAttribute("search_exbtn",search_page.get("exbtn"));//검색페이지의 페이징 이전버튼 생성여부
		model.addAttribute("search_nebtn",search_page.get("nebtn"));//검색페이지의 페이징 다음버튼 생성여부
		model.addAttribute("search_page_num",search_page.get("page_num"));//현재 검색페이지의 페이지
		model.addAttribute("search_page_screen",search_page_screen);//한화면의 검색페이지 페이징 최대개수
		model.addAttribute("search_page_count", search_page.get("page_count"));//한화면  검색페이지 페이징 개수
		model.addAttribute("search_page_start", search_page.get("page_start"));//한화면 검색페이지 페이징 시작 번호

	}



	//수정 페이지로 이동
	@RequestMapping(value = "/boardModifyPage")
	public void boardModifyPage(@RequestParam("n_num") int n_num,Model model) throws Exception{

		model.addAttribute("fileList",fdao.listFileAll(n_num));
		model.addAttribute("boardVO",bdao.boardRead(n_num));

	}

	//창닫기 순서때문에
	@RequestMapping(value = "/boardClose", method = RequestMethod.POST)
	@ResponseBody
	public void boardClose() throws Exception {

	}

	//글을 삭제
	@RequestMapping(value = "/boardDelete", method = RequestMethod.POST)
	@ResponseBody
	public String boardDelete(@RequestParam("n_num") int n_num, RedirectAttributes rttr) throws Exception {

		bdao.boardDelete(n_num);
		return "redirect:/sboard/boardListPage?page_str=1";
	}


	//글을 수정
	@RequestMapping(value = "/boardModify", method = RequestMethod.POST)
	public void boardModify(BoardVO vo) throws Exception {

		bdao.boardUpdate(vo);

	}


	//글을 추가
	@RequestMapping(value = "/boardInsert", method = RequestMethod.POST)
	public String boardInsert(BoardVO BoardVO) throws Exception {


		bdao.boardInsert(BoardVO);
		return "redirect:/sboard/boardListPage?page_str=1";
	}

	//글의 번호 찾기
	@RequestMapping(value = "/boardFindNum", method = RequestMethod.POST)
	@ResponseBody
	public Integer boardFindNum() throws Exception {


		return bdao.boardFindNum();    
	}






}



