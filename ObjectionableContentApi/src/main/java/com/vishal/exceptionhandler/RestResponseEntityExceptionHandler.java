package com.vishal.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vishal.constants.ObjContentConstants;

@ControllerAdvice(basePackages="com.vishal")
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
    public RestResponseEntityExceptionHandler() {
        super();
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleException(final RuntimeException ex, final WebRequest request) {
    	logger.error("Internal system error ", ex);
    	return handleExceptionInternal(ex, ObjContentConstants.INTERNAL_ERROR, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
