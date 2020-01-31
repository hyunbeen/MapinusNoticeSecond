package org.zerock.domain;

import java.util.Date;

public class UploadVO {
	Integer f_num;//파일의 기본키
	String f_origin;//파일의 기존이름
	String f_server;//랜덤하게 생성된 서버저장될 파일이름
	Integer f_size;//파일의 바이트 사이즈
	Date f_date;//파일의 저장시간
	Integer n_num;//파일이 업로드된 게시글의 기본키 번호

	public UploadVO() {

	}
	
	public UploadVO(String f_origin, String f_server, Integer f_size, Integer n_num) {
		super();
		this.f_origin = f_origin;
		this.f_server = f_server;
		this.f_size = f_size;
		this.n_num = n_num;
	}
	
	public Integer getF_num() {
		return f_num;
	}
	public void setF_num(Integer f_num) {
		this.f_num = f_num;
	}
	public String getF_origin() {
		return f_origin;
	}
	public void setF_origin(String f_origin) {
		this.f_origin = f_origin;
	}
	public String getF_server() {
		return f_server;
	}
	public void setF_server(String f_server) {
		this.f_server = f_server;
	}
	public Integer getF_size() {
		return f_size;
	}
	public void setF_size(Integer f_size) {
		this.f_size = f_size;
	}
	public Date getF_date() {
		return f_date;
	}
	public void setF_date(Date f_date) {
		this.f_date = f_date;
	}
	public Integer getN_num() {
		return n_num;
	}
	public void setN_num(Integer n_num) {
		this.n_num = n_num;
	}

}
