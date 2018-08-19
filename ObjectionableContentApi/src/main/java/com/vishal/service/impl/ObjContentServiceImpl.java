package com.vishal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishal.cache.ObjectionableContentCache;
import com.vishal.cache.Trie;
import com.vishal.constants.ObjContentConstants;
import com.vishal.dto.ObjContentResponseDto;
import com.vishal.enums.StatusEnum;
import com.vishal.service.ObjContentService;

@Service("objContentService")
public class ObjContentServiceImpl implements ObjContentService
{
	private static final Logger logger = LoggerFactory.getLogger(ObjContentServiceImpl.class);
	
	@Autowired
	private ObjectionableContentCache objectionableContentCache;
	
	public ObjContentResponseDto validateComments(String comment)
	{
		logger.info("Started validateComments() - {} ", comment);
		ObjContentResponseDto objContentResponseDto = new ObjContentResponseDto();
		Trie trie = objectionableContentCache.getTrie();
		
		String [] words = comment.split(ObjContentConstants.SPLIT_CHAR);
		boolean present = false;
		String lowerStr = null;
		for (int i = 0; i < words.length ; i++)
		{
			lowerStr = words[i].toLowerCase();
			logger.info("Converted to lower case - {} ", lowerStr);
			
			if(trie.search(lowerStr))
			{
				objContentResponseDto.setObjectionableContent(words[i]);
				objContentResponseDto.setMessage(StatusEnum.PRESENT.getMessage());
				present = true;
				break;
			}
		}
		if(present == false)
		{
			objContentResponseDto.setMessage(StatusEnum.NOT_PRESENT.getMessage());
		}
		
		logger.info("End validateComments()");
		
		return objContentResponseDto;
	}
	
	public String reportAbuse(String word)
	{
		logger.info("Started reportAbuse() - {} ", word);
		
		Trie trie = objectionableContentCache.getTrie();
		String lowerStr = word.toLowerCase();
		logger.info("Converted to lower case - {} ", lowerStr);
		
		if(trie.search(lowerStr))
		{
			logger.info("End reportAbuse() - {} ", StatusEnum.REPORTED.getMessage());
			return StatusEnum.REPORTED.getMessage();
		}
		else
		{
			trie.insert(lowerStr);
			logger.info("End reportAbuse() - {} ", StatusEnum.SUCCESS.getMessage());
			return StatusEnum.SUCCESS.getMessage();
		}
		
	}

}
