package org.zerock.domain;

import java.util.Date;

public class BoardVO {

	private Integer n_num;//게시글의 기본키
	private String n_user;//게시글 작성자
	private String n_title;//게시글 제목
	private Integer n_count;//게시글 조회수
	private String n_content;//게시글의 내용
	private Date n_date;//게시글의 날짜
	
	public Integer getN_num() {
		return n_num;
	}
	public void setN_num(Integer n_num) {
		this.n_num = n_num;
	}
	public String getN_user() {
		return n_user;
	}
	public void setN_user(String n_user) {
		this.n_user = n_user;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public Integer getN_count() {
		return n_count;
	}
	public void setN_count(Integer n_count) {
		this.n_count = n_count;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public Date getN_date() {
		return n_date;
	}
	public void setN_date(Date n_date) {
		this.n_date = n_date;
	}



}
