package com.vishal.dto;

public class ObjContentResponseDto 
{
	private String message;
	private String objectionableContent;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getObjectionableContent() {
		return objectionableContent;
	}
	public void setObjectionableContent(String objectionableContent) {
		this.objectionableContent = objectionableContent;
	}
	
}
