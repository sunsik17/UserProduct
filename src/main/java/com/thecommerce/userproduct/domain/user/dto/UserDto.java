package com.thecommerce.userproduct.domain.user.dto;

import com.thecommerce.userproduct.domain.user.entity.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

	private String userId;
	private String username;
	private String nickname;
	private String mobileNumber;
	private String email;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static UserDto fromEntity(User user) {
		return UserDto.builder()
			.userId(user.getUserId())
			.username(user.getUsername())
			.nickname(user.getNickname())
			.mobileNumber(user.getMobileNumber())
			.email(user.getEmail())
			.createdAt(user.getCreatedAt())
			.updatedAt(user.getUpdateAt())
			.build();
	}
}
