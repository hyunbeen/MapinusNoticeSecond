package org.zerock.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.MultiFilesVO;
import org.zerock.domain.UploadVO;
import org.zerock.persistence.FileDAO;
import org.zerock.service.FileDownloadService;
import org.zerock.service.FileUploadDeleteService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/file")
public class FileController {


	@Inject
	private FileDAO fdao;

	@Inject
	private FileDownloadService fds;

	@Inject
	private FileUploadDeleteService fuds;
	
	@Autowired
	@Qualifier("uploadFile")
	private UploadVO uploadFile;
	
	@RequestMapping(value = "/fileUpload")
	@ResponseBody
	public void fileUpload(MultiFilesVO multiFiles,String title, MultipartFile file1,Model model,HttpServletRequest request,int n_num) throws IllegalStateException, IOException {

		String project = request.getContextPath().substring(1,request.getContextPath().length());
		//������ ���ε�� path ���� 
		String saveDir = System.getProperty("user.dir")+"\\"+project+"\\WebContent\\resources\\upload"; 


		if(multiFiles.getFile1()!=null) {
			for(MultipartFile file : multiFiles.getFile1()){


				// ������ path�� �������� 
				File serverFile = new File(saveDir + File.separator + getUuid());
				String serverName = serverFile.getName();
				String originalName = file.getOriginalFilename();
				int file_size = (int)file.getSize();

				uploadFile.setF_origin(originalName);
				uploadFile.setF_server(serverName);
				uploadFile.setF_size(file_size);
				uploadFile.setN_num(n_num);
			
				fdao.fileCreate(uploadFile);

				// ���������� ������ ���Ϸ� �̵�
				file.transferTo(serverFile);


			}
		}



	}

	@RequestMapping(value = "/fileDownload")
	public void fileDownload(HttpServletRequest request,String f_origin , String f_server,HttpServletResponse response,Model model) throws Exception {

		//÷�� ������ ����
		Map<String, Object> fileInfo = new HashMap<String, Object>();

		String project = request.getContextPath().substring(1,request.getContextPath().length());
		//������ ���ε�� path ���� 
		String saveDir = System.getProperty("user.dir")+"\\"+project+"\\WebContent\\resources\\upload"; 

		fileInfo.put("fileUploadPath", saveDir);
		fileInfo.put("fileLogicName",  f_origin);
		fileInfo.put("filePhysicName", f_server);

		model.addAttribute("downloadFile", fileInfo);

		fds.renderMergedOutputModel((Map)model, request, response);
	}

	//���� ÷������ �� ���õ��� ���� ÷�������� db���� �����Ѵ�
	@RequestMapping(value = "/fileBaseDelete")
	@ResponseBody
	public void fileBaseDelete(@RequestBody String req) throws Exception {
		JSONObject json = JSONObject.fromObject(req);//�޾ƿ� �Ķ���� json �������� ��ȯ
		List<String> fNumList = (List<String>) json.get("data");//json �и�
		for(int i=0;i<fNumList.size();i++) {
			fdao.fileBaseDelete(Integer.parseInt(fNumList.get(i)));//dao��ü�� ���� ����
		}



	}

	//���� ÷������ �� ���õ��� ���� ÷�������� ���ε� �������� �����Ѵ�
	@RequestMapping(value = "/fileUploadDelete")
	@ResponseBody
	public void fileUploadDelete(@RequestBody String req,HttpServletRequest request) throws Exception {
	
		JSONObject json = JSONObject.fromObject(req);//�޾ƿ� �Ķ���� json �������� ��ȯ
		
		List<String> fServerList = (List<String>) json.get("data");//json �и�

		for(int i=0;i<fServerList.size();i++) {
		
			fuds.fileUploadDelete(fServerList.get(i),request);//���ε� ��ġ�� �ִ� ���� ����
			
		}



	}

	//������ ���� �̸��� ����
	public static String getUuid() { 

		return UUID.randomUUID().toString().replaceAll("-", ""); 
	}

}
