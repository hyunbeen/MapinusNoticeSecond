package org.zerock.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.zerock.domain.UploadVO;
import org.zerock.persistence.FileDAO;

@Service
public class FileUploadDeleteService {

	


	public void fileUploadDelete(String f_server,HttpServletRequest request) throws Exception {
		
		String project = request.getContextPath().substring(1,request.getContextPath().length());
		String saveDir = System.getProperty("user.dir")+"\\"+project+"\\WebContent\\resources\\upload"; 
		String fileName = "";

		//업로드된 파일을 삭제
		fileName = f_server;
		File file = new File(saveDir+"\\"+fileName);
		file.delete();	
		
	}

}
