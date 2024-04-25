package com.thecommerce.userproduct.exception;

import static com.thecommerce.userproduct.exception.contants.ErrorCode.INVALID_REQUEST;
import static com.thecommerce.userproduct.exception.contants.ErrorCode.SERVER_ERROR;

import com.thecommerce.userproduct.exception.contants.ErrorCode;
import com.thecommerce.userproduct.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(UserServiceException.class)
	public ResponseEntity<ErrorResponse> handleAuctionException(UserServiceException e) {
		ErrorCode errorCode = e.getErrorCode();
		log.error("{} is occurred", errorCode);

		return ResponseEntity.status(e.getErrorCode().getHttpStatus())
			.body(new ErrorResponse(errorCode, errorCode.getDescription()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e) {
		log.error("MethodArgumentNotValidException is occurred.", e);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription()));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
		DataIntegrityViolationException e) {
		log.error("DataIntegrityViolationException is occurred.", e);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		log.error("Exception is occurred.", e);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
			new ErrorResponse(SERVER_ERROR, SERVER_ERROR.getDescription()));
	}
}
