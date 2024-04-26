package com.thecommerce.userproduct.service.user;

import static com.thecommerce.userproduct.exception.contants.ErrorCode.ALREADY_EMAIL_EXIST;
import static com.thecommerce.userproduct.exception.contants.ErrorCode.ALREADY_MOBILE_NUMBER_EXIST;
import static com.thecommerce.userproduct.exception.contants.ErrorCode.ALREADY_NICKNAME_EXIST;
import static com.thecommerce.userproduct.exception.contants.ErrorCode.ALREADY_USERID_EXIST;
import static com.thecommerce.userproduct.exception.contants.ErrorCode.NOT_FOUND_USER;

import com.thecommerce.userproduct.domain.user.dto.RegisterUser;
import com.thecommerce.userproduct.domain.user.dto.UpdateUser;
import com.thecommerce.userproduct.domain.user.dto.UserDto;
import com.thecommerce.userproduct.domain.user.entity.User;
import com.thecommerce.userproduct.domain.user.repository.UserRepository;
import com.thecommerce.userproduct.exception.UserServiceException;
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

	public UserDto update(String userId, UpdateUser.Request request) {
		User user = userRepository.findByUserId(userId).orElseThrow(
			() -> new UserServiceException(NOT_FOUND_USER)
		);

		user.update(request.getNickname(), request.getMobileNumber(), request.getEmail());
		return UserDto.fromEntity(userRepository.save(user));
	}

	private void validateRegister(RegisterUser.Request request) {
		validateRegisterUserId(request.getUserId());
		validateRegisterEmail(request.getEmail());
		validateRegisterMobileNumber(request.getMobileNumber());
		validateRegisterNickname(request.getNickname());
	}

	private void validateRegisterEmail(String email) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new UserServiceException(ALREADY_EMAIL_EXIST);
		}
	}

	private void validateRegisterMobileNumber(String mobileNumber) {
		if (userRepository.findByMobileNumber(mobileNumber).isPresent()) {
			throw new UserServiceException(ALREADY_MOBILE_NUMBER_EXIST);
		}
	}

	private void validateRegisterNickname(String nickname) {
		if (userRepository.findByNickname(nickname).isPresent()) {
			throw new UserServiceException(ALREADY_NICKNAME_EXIST);
		}
	}

	private void validateRegisterUserId(String userId) {
		if (userRepository.findByUserId(userId).isPresent()) {
			throw new UserServiceException(ALREADY_USERID_EXIST);
		}
	}
}
