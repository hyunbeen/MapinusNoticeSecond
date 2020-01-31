package org.zerock.persistence;

import java.util.*;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;
import org.zerock.domain.ReplyVO;
import org.zerock.domain.UploadVO;

@Repository
public class FileDAO{

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.FileMapper";
	
	//업로드된 리스트를 가져온다
	public List<UploadVO> listFileAll(int n_num) throws Exception {
		return session.selectList(namespace+".selectFile",n_num);
	}

	//파일 업로드
	public void fileCreate(UploadVO uploadFile) {
		session.insert(namespace + ".fileCreate", uploadFile);
	}
	
	//기존 첨부파일 중 체크되지 않은 것들은 삭제
	public void fileBaseDelete(int f_num) {
		session.delete(namespace+".fileBaseDelete",f_num);
	}
	
}
