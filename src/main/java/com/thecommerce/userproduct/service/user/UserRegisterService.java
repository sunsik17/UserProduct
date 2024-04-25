package com.thecommerce.userproduct.service.user;

import com.thecommerce.userproduct.domain.user.dto.RegisterUser;
import com.thecommerce.userproduct.domain.user.dto.UserDto;
import com.thecommerce.userproduct.domain.user.entity.User;
import com.thecommerce.userproduct.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

	private final UserRepository userRepository;

	public UserDto join(RegisterUser.Request request) {

		validateRegister(request);

		return UserDto.fromEntity(userRepository.save(User.builder()
			.userId(request.getUserId())
			.password(request.getPassword())
			.nickname(request.getNickname())
			.username(request.getUsername())
			.mobileNumber(request.getMobileNumber())
			.email(request.getEmail())
			.build()));
	}

	private void validateRegister(RegisterUser.Request request) {
		validateRegisterUserId(request.getUserId());
		validateRegisterEmail(request.getEmail());
		validateRegisterMobileNumber(request.getMobileNumber());
		validateRegisterNickname(request.getNickname());
	}

	private void validateRegisterEmail(String email) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new RuntimeException("이메일 중복");
		}
	}

	private void validateRegisterMobileNumber(String mobileNumber) {
		if (userRepository.findByMobileNumber(mobileNumber).isPresent()) {
			throw new RuntimeException("모바일 번호 중복");
		}
	}

	private void validateRegisterNickname(String nickname) {
		if (userRepository.findByNickname(nickname).isPresent()) {
			throw new RuntimeException("닉네임 중복");
		}
	}

	private void validateRegisterUserId(String userId) {
		if (userRepository.findByUserId(userId).isPresent()) {
			throw new RuntimeException("아이디 중복");
		}
	}
}
