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



	
	//������ ���
	public Map<String,Integer> calculatePage(int page_num, int last_page_num, int size, int page_iterative, int page_screen) {
		Map<String,Integer> result = new HashMap();

		int data_start = 0;
		int page_count = 0;
		int page_start = 0;
		int exbtn = 0;
		int nebtn = 0;

		//������ ���� ���
		if(page_num < 1){
			page_num = 1; //���� �������� ���
		}else if(page_num > last_page_num){
			page_num = last_page_num; //�������� �Ѱ��������� �Ѿ ���
		}
		
		result.put("page_num", page_num);
		
		//������ ���� ���
		if(size<=page_iterative) {
			data_start = 0; //�������� �������� �϶�
		}
		else if(page_iterative*(page_num )>size) {
			data_start = (size/page_iterative)*page_iterative; //�������� �����Ͱ� ������ ���� �Ѿ����
		}
		else if(page_num >=1) {
			data_start = page_iterative*(page_num -1); //�Ϲ����� �������� ������ �϶�
		}else {
			data_start = 0; //���� ������ �϶�
		}
		
		result.put("data_start", data_start);
		
		if(page_num > 1){
			exbtn = 1;
		}//���� ��ư�� �������� ���

		if(last_page_num>page_screen && page_num<last_page_num){
			nebtn = 1;
		}//������ư�� �������� ���
		
		result.put("exbtn",exbtn);
		result.put("nebtn",nebtn);

		if(size == 0){
			page_count = 0; //�����Ͱ� ���� ���
		}else if(last_page_num<=page_screen){ //���� �������� ȭ�� ǥ�� ������ ���� ���� ���
			page_start = 1; 
			page_count = last_page_num;
		}else if((last_page_num-page_screen+1)>page_num){ //���� �������� �ǵ� �ټ��� ���������� ���� ���
			page_count = page_screen;
			page_start = page_screen*((page_num-1)/page_screen)+1;
		}else{ //���� �������� �ǵ� �ټ��������� �ϳ��ϰ��
			page_start = last_page_num - page_screen + 1;
			page_count = page_screen;
		}
		
		result.put("page_count",page_count);
		result.put("page_start",page_start);
		
		return result;
	}




}
