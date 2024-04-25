package com.thecommerce.userproduct.exception.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	INVALID_REQUEST(BAD_REQUEST, "잘못 된 요청"),
	SERVER_ERROR(INTERNAL_SERVER_ERROR, "내부 서버 오류 발생"),

	ALREADY_EMAIL_EXIST (BAD_REQUEST, "이메일 중복"),
	ALREADY_NICKNAME_EXIST (BAD_REQUEST, "닉네임 중복"),
	ALREADY_MOBILE_NUMBER_EXIST (BAD_REQUEST, "모바일 번호 중복"),
	ALREADY_USERID_EXIST (BAD_REQUEST, "유저 아이디 중복");

	private final HttpStatus httpStatus;
	private final String description;
}
