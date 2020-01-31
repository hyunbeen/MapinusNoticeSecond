package org.zerock.persistence;

import java.util.*;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.zerock.domain.ReplyVO;

@Repository
public class ReplyDAO{

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.ReplyMapper";
	
	@Autowired
	@Qualifier("parentVO")
	private ReplyVO parentVO;
	
	@Autowired
	@Qualifier("voReply")
	private ReplyVO voReply;
	
	@Autowired
	@Qualifier("replyDeleteVO")
	private ReplyVO replyDeleteVO;
	
	//리뷰 리스트 파트 가져오기
	public List<ReplyVO> replyListPart(int n_num,int re_page_num,int re_page_iterative) throws Exception {
		HashMap map = new HashMap<String,Object>();
		map.put("n_num",n_num);
		map.put("re_page_start",re_page_num);
		map.put("re_page_end",re_page_iterative);
		return session.selectList(namespace + ".replyListPart", map);
	}

	//리뷰 생성하기
	public void replyCreate(ReplyVO vo,int n_num) throws Exception {
		HashMap map = new HashMap<String,Object>();
		map.put("replyVO",vo);
		map.put("n_num",n_num);
		session.insert(namespace + ".replyCreate", map);
	}

	//리뷰 수정하기
	public void replyUpdate(ReplyVO vo) throws Exception {

		session.update(namespace + ".replyUpdate", vo);
	}

	// 리플 삭제하기 , 리플의 하위 부모 업데이트
	public void replyDelete(int r_num) throws Exception {
		
		voReply.setR_num(r_num);
		replyDeleteVO = session.selectOne(namespace + ".replyFindOne", voReply); 
		int location = 0;
		int limit = 0;

		//댓글의 위치 파악
		for(int i=1;i<=8;i++) {
			int pow = (int)Math.pow(10, i);
			if((replyDeleteVO.getR_group()%pow)/(pow/10)>0) {
				location = i;
				limit = replyDeleteVO.getR_group()+(pow/10); //삭제될 범위를 지정
				break;
			}
		}

		HashMap map = new HashMap();
		map.put("start", replyDeleteVO.getR_group());
		map.put("end", limit);
		session.delete(namespace + ".replyDelete", map); //글 자체를 삭제
		session.update(namespace + ".replyUpdateDelete", map); //상위 글이 삭제 되었다는 것을 표시
		

	}

	//리뷰의 개수 가져오기
	public List<ReplyVO> replyListSize(int n_num) throws Exception {
		HashMap map = new HashMap<String,Object>();
		map.put("n_num",n_num);
		return session.selectList(namespace + ".replyListSize", map);
	}

	//리뷰의 리뷰 그룹짓기
	public ReplyVO replyFindOne(ReplyVO vo) throws Exception {

		return session.selectOne(namespace + ".replyFindOne", vo);
	}

	//리뷰의 리뷰 그룹추가
	public void replyCreateGroup(ReplyVO vo) throws Exception {

		session.insert(namespace + ".replyCreateGroup",vo);

	}


	public int replyChild(ReplyVO selectVO) {
		session.update(namespace + ".replyChildUpdate", selectVO);
		parentVO  = session.selectOne(namespace + ".replyFindOne", selectVO);
		return parentVO.getR_child();
	}




}
