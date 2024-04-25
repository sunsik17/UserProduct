package com.thecommerce.userproduct.domain.user.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RegisterUser {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Request {
		private String userId;
		private String password;
		private String nickname;
		private String username;
		private String mobileNumber;
		@Email
		private String email;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Response {
		private String userId;
		private String nickname;
		private LocalDateTime createdAt;

		public static Response from(UserDto userDto) {
			return Response.builder()
				.userId(userDto.getUserId())
				.nickname(userDto.getNickname())
				.createdAt(userDto.getCreatedAt())
				.build();
		}
	}
}
