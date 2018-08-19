package com.vishal.service;

import com.vishal.dto.ObjContentResponseDto;

public interface ObjContentService 
{
	public ObjContentResponseDto validateComments(String comment);
	public String reportAbuse(String word);
}
