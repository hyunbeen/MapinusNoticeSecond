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
	
	//���� ����Ʈ ��Ʈ ��������
	public List<ReplyVO> replyListPart(int n_num,int re_page_num,int re_page_iterative) throws Exception {
		HashMap map = new HashMap<String,Object>();
		map.put("n_num",n_num);
		map.put("re_page_start",re_page_num);
		map.put("re_page_end",re_page_iterative);
		return session.selectList(namespace + ".replyListPart", map);
	}

	//���� �����ϱ�
	public void replyCreate(ReplyVO vo,int n_num) throws Exception {
		HashMap map = new HashMap<String,Object>();
		map.put("replyVO",vo);
		map.put("n_num",n_num);
		session.insert(namespace + ".replyCreate", map);
	}

	//���� �����ϱ�
	public void replyUpdate(ReplyVO vo) throws Exception {

		session.update(namespace + ".replyUpdate", vo);
	}

	// ���� �����ϱ� , ������ ���� �θ� ������Ʈ
	public void replyDelete(int r_num) throws Exception {
		
		voReply.setR_num(r_num);
		replyDeleteVO = session.selectOne(namespace + ".replyFindOne", voReply); 
		int location = 0;
		int limit = 0;

		//����� ��ġ �ľ�
		for(int i=1;i<=8;i++) {
			int pow = (int)Math.pow(10, i);
			if((replyDeleteVO.getR_group()%pow)/(pow/10)>0) {
				location = i;
				limit = replyDeleteVO.getR_group()+(pow/10); //������ ������ ����
				break;
			}
		}

		HashMap map = new HashMap();
		map.put("start", replyDeleteVO.getR_group());
		map.put("end", limit);
		session.delete(namespace + ".replyDelete", map); //�� ��ü�� ����
		session.update(namespace + ".replyUpdateDelete", map); //���� ���� ���� �Ǿ��ٴ� ���� ǥ��
		

	}

	//������ ���� ��������
	public List<ReplyVO> replyListSize(int n_num) throws Exception {
		HashMap map = new HashMap<String,Object>();
		map.put("n_num",n_num);
		return session.selectList(namespace + ".replyListSize", map);
	}

	//������ ���� �׷�����
	public ReplyVO replyFindOne(ReplyVO vo) throws Exception {

		return session.selectOne(namespace + ".replyFindOne", vo);
	}

	//������ ���� �׷��߰�
	public void replyCreateGroup(ReplyVO vo) throws Exception {

		session.insert(namespace + ".replyCreateGroup",vo);

	}


	public int replyChild(ReplyVO selectVO) {
		session.update(namespace + ".replyChildUpdate", selectVO);
		parentVO  = session.selectOne(namespace + ".replyFindOne", selectVO);
		return parentVO.getR_child();
	}




}
