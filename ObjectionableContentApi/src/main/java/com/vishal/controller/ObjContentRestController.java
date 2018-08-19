package com.vishal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.constants.ObjContentConstants;
import com.vishal.dto.ObjContentResponseDto;
import com.vishal.service.ObjContentService;



@RestController
public class ObjContentRestController 
{
	private static final Logger logger = LoggerFactory.getLogger(ObjContentRestController.class);
	private static final String REGEX = "[^a-zA-Z\\s+]";
	@Autowired
	private ObjContentService objContentService;
	
	@RequestMapping(value = "/validateComments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjContentResponseDto> validateComments (@RequestBody String comment) 
    {
		comment = comment.replaceAll(REGEX, "");
		logger.info("Started validateComments() - {} ", comment);
		ObjContentResponseDto objContentResponseDto = objContentService.validateComments(comment);
		logger.info("End validateComments()");
		
		return new ResponseEntity<ObjContentResponseDto>(objContentResponseDto, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/reportAbuseWord", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> reportAbuse(@RequestBody String word) 
    {
		try
		{
			word = word.replaceAll(REGEX, "");
			logger.info("Started reportAbuse() - {} ", word);
			String result = objContentService.reportAbuse(word);
			logger.info("End reportAbuse()");
			
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Error in reportAbuse() {}", ex);
			return new ResponseEntity<String>(ObjContentConstants.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
