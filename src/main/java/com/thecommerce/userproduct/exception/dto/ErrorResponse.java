package com.thecommerce.userproduct.exception.dto;

import com.thecommerce.userproduct.exception.contants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private ErrorCode errorCode;
	private String message;

}
