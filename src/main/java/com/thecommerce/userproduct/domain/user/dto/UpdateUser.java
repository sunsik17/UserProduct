package com.thecommerce.userproduct.domain.user.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateUser {
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request {
		@Email
		private String email;
		private String mobileNumber;
		private String nickname;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Response {
		private String nickname;
		private String mobile;
		private String email;
		private LocalDateTime updatedAt;
		public static Response from(UserDto userDto) {
			return Response.builder()
				.mobile(userDto.getMobileNumber())
				.email(userDto.getEmail())
				.nickname(userDto.getNickname())
				.updatedAt(userDto.getUpdatedAt())
				.build();
		}
	}
}
