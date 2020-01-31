package org.zerock.domain;

import org.springframework.web.multipart.MultipartFile;

public class MultiFilesVO {

	private String title;

	private MultipartFile[] file1;//여러개의 파일을 담는다

	public String getTitle() {

		return title;

	}

	public void setTitle(String title) {

		this.title = title;

	}

	public MultipartFile[] getFile1() {

		return file1;

	}

	public void setFile1(MultipartFile[] file1) {

		this.file1 = file1;

	}


}
