package com.thecommerce.userproduct.exception;

import static com.thecommerce.userproduct.exception.contants.ErrorCode.INVALID_REQUEST;
import static com.thecommerce.userproduct.exception.contants.ErrorCode.SERVER_ERROR;

import com.thecommerce.userproduct.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(UserServiceException.class)
	public ErrorResponse handleAuctionException(UserServiceException e) {
		log.error("{} is occurred", e.getErrorCode());

		return new ErrorResponse(e.getErrorCode().getHttpStatus(), e.getErrorCode(), e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("MethodArgumentNotValidException is occurred.", e);

		return new ErrorResponse(INVALID_REQUEST.getHttpStatus(), INVALID_REQUEST, INVALID_REQUEST.getDescription());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		log.error("DataIntegrityViolationException is occurred.", e);

		return new ErrorResponse(INVALID_REQUEST.getHttpStatus(),
			INVALID_REQUEST,
			INVALID_REQUEST.getDescription());
	}
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(Exception e) {
		log.error("Exception is occurred.", e);

		return new ErrorResponse(SERVER_ERROR.getHttpStatus(),
			SERVER_ERROR,
			SERVER_ERROR.getDescription());
	}
}
