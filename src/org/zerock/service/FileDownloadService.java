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
		// ��ü�� ������ �� Content Type�� ������ ���� ����
		///setContentType("application/download; charset=utf-8");
		
	}


	public void renderMergedOutputModel(
			Map<String, Object> model,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception {

		Map<String, Object> fileInfo = (Map<String, Object>) model.get("downloadFile"); // ���� ����

		String fileUploadPath = (String) fileInfo.get("fileUploadPath");    // ���� ���ε� ���
		String fileLogicName = (String) fileInfo.get("fileLogicName");      // ȭ�鿡 ǥ�õ� ���� ��
		String filePhysicName = (String) fileInfo.get("filePhysicName");    // ���� ����� ���� �̸�

		File file = new File(fileUploadPath, filePhysicName);
		 
		//���Ͽ� ���� �� ����
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
		
		//���Ͽ� ���� ��� ����
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();

		FileInputStream fis = null;
		
		//���Ͽ� �ٿ�ε�
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

