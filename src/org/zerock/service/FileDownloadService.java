package org.zerock.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
@Service
public class FileDownloadService{

	public FileDownloadService() {
		// 객체가 생성될 때 Content Type을 다음과 같이 변경
		///setContentType("application/download; charset=utf-8");
		
	}


	public void renderMergedOutputModel(
			Map<String, Object> model,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception {

		Map<String, Object> fileInfo = (Map<String, Object>) model.get("downloadFile"); // 파일 정보

		String fileUploadPath = (String) fileInfo.get("fileUploadPath");    // 파일 업로드 경로
		String fileLogicName = (String) fileInfo.get("fileLogicName");      // 화면에 표시될 파일 이
		String filePhysicName = (String) fileInfo.get("filePhysicName");    // 실제 저장된 파일 이름

		File file = new File(fileUploadPath, filePhysicName);
		 
		//파일에 대한 글 설정
		response.setContentType("application/download; charset=utf-8");
		response.setContentLength((int) file.length());
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileName = null;

		if(ie) {
			fileName = URLEncoder.encode(fileLogicName, "utf-8");
		} else {
			fileName = new String(fileLogicName.getBytes("utf-8"), "iso-8859-1");
		}
		
		//파일에 대한 경로 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();

		FileInputStream fis = null;
		
		//파일에 다운로드
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		}finally {
			if(fis != null)
				try {
					fis.close();
				}catch(IOException ex) {
			}
		}
		out.flush();
	}
}

