package com.thecommerce.userproduct.exception;

import com.thecommerce.userproduct.exception.contants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceException extends RuntimeException{

	private ErrorCode errorCode;
	private String message;

	public UserServiceException(ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.message = errorCode.getDescription();
	}
}
