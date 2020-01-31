package org.zerock.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;
import org.zerock.domain.UploadVO;

@Repository
public class BoardDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "org.zerock.mapper.BoardMapper";

	//게시판글 추가
	public void boardInsert(BoardVO vo) throws Exception {
		session.update(namespace + ".boardInsert", vo);

	}

	//게시판 글 상세보기
	public BoardVO boardRead(Integer n_num) throws Exception {
		return session.selectOne(namespace + ".boardRead",  n_num);
	}

	//게시판 글 업데이트 
	public void boardUpdate(BoardVO vo) throws Exception {
		session.update(namespace + ".boardUpdate", vo);
	}

	//게시판 글 삭제
	public void boardDelete(Integer n_num) throws Exception {
		session.delete(namespace + ".boardDelete", n_num);
	}

	//게시판 글 가져오기
	public List<BoardVO> boardListPart(int page_num,int page_iterative) throws Exception {
		HashMap map = new HashMap<String,Object>();
		map.put("page_start",page_num);
		map.put("page_end",page_iterative);

		return session.selectList(namespace + ".boardListPart",map);
	}

	//게시판 글 조회수 업데이트
	public void boardUpdateCount(int n_num) throws Exception {

		session.update(namespace + ".boardUpdateCount", n_num);
	}

	//검색 리스트 가져오기
	public List<BoardVO> boardSearchListPart(String cond, String word, int search_page_num,int search_page_iterative) throws Exception {
		HashMap map = new HashMap<String,Object>();
		
		
		map.put("search_page_start",search_page_num);
		map.put("search_page_end",search_page_iterative);
		map.put("search_word",word);
		map.put("search_cond",cond);
		return session.selectList(namespace + ".boardSearchListPart",map);
	}

	//검색 리스트의 수 가져오기 
	public List<BoardVO> boardSearchListSize(String cond, String word) throws Exception {
		HashMap map = new HashMap<String,Object>();
		map.put("search_word",word);
		map.put("search_cond",cond);
		return session.selectList(namespace + ".boardSearchListSize",map);
	}

	//게시글의 수 가져오기	
	public List<BoardVO> boardListSize() throws Exception {
		HashMap map = new HashMap<String,Object>();


		return session.selectList(namespace + ".boardListSize");
	}

	public Integer boardFindNum() {
		session.insert(namespace + ".boardBaseCreate");
		
		return session.selectOne(namespace + ".boardSelectKey");
	}


}
