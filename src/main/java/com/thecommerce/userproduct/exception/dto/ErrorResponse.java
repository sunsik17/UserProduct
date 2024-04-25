package com.thecommerce.userproduct.exception.dto;

import com.thecommerce.userproduct.exception.contants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private HttpStatus httpStatus;
	private ErrorCode errorCode;
	private String message;

	public static ErrorResponse from(ErrorCode errorCode) {
		return new ErrorResponse(errorCode.getHttpStatus(), errorCode, errorCode.getDescription());
	}
}
