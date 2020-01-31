package org.zerock.domain;

import java.util.Date;

public class ReplyVO {

	private Integer r_num;//댓글 기본키
	private Integer n_num;//게시글의 기본키
	private String r_content;//댓글 내용
	private Date r_date;//댓글 작성 날짜
	private String r_user;//댓글 작성자
	private Integer r_group;//댓글 그룹
	private Integer r_order;//댓글 순서
	private String increase;//글의 가중치를 저장함
	private Integer r_child;//댓글의 개수
	private Integer r_delete;//삭제된 댓글 확인

	public Integer getR_delete() {
		return r_delete;
	}
	public void setR_delete(Integer r_delete) {
		this.r_delete = r_delete;
	}
	public Integer getR_child() {
		return r_child;
	}
	public void setR_child(Integer r_child) {
		this.r_child = r_child;
	}
	public String getIncrease() {
		return increase;
	}
	public void setIncrease(String increase) {
		this.increase = increase;
	}
	public Integer getR_group() {
		return r_group;
	}
	public void setR_group(Integer r_group) {
		this.r_group = r_group;
	}
	public Integer getR_order() {
		return r_order;
	}
	public void setR_order(Integer r_order) {
		this.r_order = r_order;
	}
	public Integer getR_num() {
		return r_num;
	}
	public void setR_num(Integer r_num) {
		this.r_num = r_num;
	}
	public Integer getN_num() {
		return n_num;
	}
	public void setN_num(Integer n_num) {
		this.n_num = n_num;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	public String getR_user() {
		return r_user;
	}
	public void setR_user(String r_user) {
		this.r_user = r_user;
	}


}
