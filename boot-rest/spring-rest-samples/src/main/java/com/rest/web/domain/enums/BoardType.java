package com.rest.web.domain.enums;

public enum BoardType {
	
	notice("공지게시판"),
	free("자유게시판");
	
	private String value;
	
	BoardType(String vlaue){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
