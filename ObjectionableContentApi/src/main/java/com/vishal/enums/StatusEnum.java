package com.vishal.enums;

public enum StatusEnum 
{
	PRESENT("This comment contains objectionable content"),
	NOT_PRESENT("This comment does not contains objectionable content"),
	SUCCESS("Reported successfully"),
	REPORTED("Already Reported");
	
	private String message;
	
	private StatusEnum(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}
