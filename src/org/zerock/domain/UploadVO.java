package org.zerock.domain;

import java.util.Date;

public class UploadVO {
	Integer f_num;//������ �⺻Ű
	String f_origin;//������ �����̸�
	String f_server;//�����ϰ� ������ ��������� �����̸�
	Integer f_size;//������ ����Ʈ ������
	Date f_date;//������ ����ð�
	Integer n_num;//������ ���ε�� �Խñ��� �⺻Ű ��ȣ

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
