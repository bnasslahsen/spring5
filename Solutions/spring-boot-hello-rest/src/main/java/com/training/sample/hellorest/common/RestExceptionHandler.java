package com.training.sample.hellorest.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<ErrorDetail> handleNotFoundException(NotFoundException e) {
        return error(HttpStatus.NOT_FOUND, e);
    }

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorDetail> handleRunTimeException(Exception e) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}

    private ResponseEntity<ErrorDetail> error(HttpStatus status, Exception e) {
        LOG.error("Exception : ", e);
        return ResponseEntity.status(status).body(new ErrorDetail(e.getMessage()));
    }
}
