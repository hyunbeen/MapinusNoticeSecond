package org.zerock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.persistence.BoardDAO;

@Service
public class PagingService{



	
	//페이지 계산
	public Map<String,Integer> calculatePage(int page_num, int last_page_num, int size, int page_iterative, int page_screen) {
		Map<String,Integer> result = new HashMap();

		int data_start = 0;
		int page_count = 0;
		int page_start = 0;
		int exbtn = 0;
		int nebtn = 0;

		//페이지 범위 계산
		if(page_num < 1){
			page_num = 1; //음수 페이지의 경우
		}else if(page_num > last_page_num){
			page_num = last_page_num; //페이지가 한계페이지를 넘어설 경우
		}
		
		result.put("page_num", page_num);
		
		//데이터 범위 계산
		if(size<=page_iterative) {
			data_start = 0; //페이지가 한페이지 일때
		}
		else if(page_iterative*(page_num )>size) {
			data_start = (size/page_iterative)*page_iterative; //페이지의 데이터가 데이터 수를 넘어섰을때
		}
		else if(page_num >=1) {
			data_start = page_iterative*(page_num -1); //일반적인 페이지의 데이터 일때
		}else {
			data_start = 0; //음수 페이지 일때
		}
		
		result.put("data_start", data_start);
		
		if(page_num > 1){
			exbtn = 1;
		}//이전 버튼의 생성유무 계산

		if(last_page_num>page_screen && page_num<last_page_num){
			nebtn = 1;
		}//다음버튼의 생성유무 계산
		
		result.put("exbtn",exbtn);
		result.put("nebtn",nebtn);

		if(size == 0){
			page_count = 0; //데이터가 없을 경우
		}else if(last_page_num<=page_screen){ //현재 페이지가 화면 표시 페이지 보다 작을 경우
			page_start = 1; 
			page_count = last_page_num;
		}else if((last_page_num-page_screen+1)>page_num){ //현재 페이지가 맨뒤 다섯개 페이지보다 작을 경우
			page_count = page_screen;
			page_start = page_screen*((page_num-1)/page_screen)+1;
		}else{ //현재 페이지가 맨뒤 다섯페이지중 하나일경우
			page_start = last_page_num - page_screen + 1;
			page_count = page_screen;
		}
		
		result.put("page_count",page_count);
		result.put("page_start",page_start);
		
		return result;
	}




}
