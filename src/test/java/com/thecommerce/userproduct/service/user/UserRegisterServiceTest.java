package com.thecommerce.userproduct.service.user;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.thecommerce.userproduct.domain.user.dto.RegisterUser;
import com.thecommerce.userproduct.domain.user.dto.RegisterUser.Request;
import com.thecommerce.userproduct.domain.user.entity.User;
import com.thecommerce.userproduct.domain.user.repository.UserRepository;
import com.thecommerce.userproduct.exception.UserServiceException;
import com.thecommerce.userproduct.exception.contants.ErrorCode;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserRegisterServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserRegisterService userRegisterService;

	@Test
	@DisplayName("회원가입 성공")
	void successRegisterUser() {
	    //given
		given(userRepository.save(any()))
			.willReturn(User.builder()
				.userId("1")
				.password("1")
				.username("1")
				.mobileNumber("1")
				.nickname("1")
				.email("17sunsik@gmail.com")
				.build());
	    //when
		userRegisterService.join(new Request(
			"17sunsik",
			"1234",
			"nj2",
			"nss",
			"01033538090",
			"17sunsik@gmail.com"
		));
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
	    //then
		verify(userRepository, times(1)).save(captor.capture());
		assertEquals("17sunsik", captor.getValue().getUserId());
		assertEquals("1234", captor.getValue().getPassword());
		assertEquals("17sunsik@gmail.com", captor.getValue().getEmail());
		assertEquals("01033538090", captor.getValue().getMobileNumber());
		assertEquals("nss", captor.getValue().getUsername());
		assertEquals("nj2", captor.getValue().getNickname());
	}

	@Test
	@DisplayName("회원가입 실패 - 유저 아이디 중복")
	void failRegisterUserAlreadyExistUserId() {
		//given
		given(userRepository.findByUserId(any()))
			.willReturn(Optional.ofNullable(User.builder()
				.userId("17sunsik")
				.password("1234")
				.username("ss")
				.mobileNumber("01033538090")
				.nickname("nj2")
				.email("17sunsik@gmail.com")
				.build()));
		//when
		UserServiceException userServiceException = assertThrows(UserServiceException.class,
			() -> userRegisterService.join(new RegisterUser.Request(
				"17sunsik",
				"1234",
				"nj2",
				"nss",
				"01033538090",
				"17sunsik@gmail.com"
			)));
		//then
		assertEquals(ErrorCode.ALREADY_USERID_EXIST, userServiceException.getErrorCode());
	}

	@Test
	@DisplayName("회원가입 실패 - 이메일 중복")
	void failRegisterUserAlreadyExistEmail() {
		//given
		given(userRepository.findByEmail(any()))
			.willReturn(Optional.ofNullable(User.builder()
				.userId("17sunsik")
				.password("1234")
				.username("ss")
				.mobileNumber("01033538090")
				.nickname("nj2")
				.email("17sunsik@gmail.com")
				.build()));
		//when
		UserServiceException userServiceException = assertThrows(UserServiceException.class,
			() -> userRegisterService.join(new RegisterUser.Request(
				"17sunsik",
				"1234",
				"nj2",
				"nss",
				"01033538090",
				"17sunsik@gmail.com"
			)));
		//then
		assertEquals(ErrorCode.ALREADY_EMAIL_EXIST, userServiceException.getErrorCode());
	}

	@Test
	@DisplayName("회원가입 실패 - 모바일 번호 중복")
	void failRegisterUserAlreadyExistMobileNumber() {
		//given
		given(userRepository.findByMobileNumber(any()))
			.willReturn(Optional.ofNullable(User.builder()
				.userId("17sunsik")
				.password("1234")
				.username("ss")
				.mobileNumber("01033538090")
				.nickname("nj2")
				.email("17sunsik@gmail.com")
				.build()));
		//when
		UserServiceException userServiceException = assertThrows(UserServiceException.class,
			() -> userRegisterService.join(new RegisterUser.Request(
				"17sunsik",
				"1234",
				"nj2",
				"nss",
				"01033538090",
				"17sunsik@gmail.com"
			)));
		//then
		assertEquals(ErrorCode.ALREADY_MOBILE_NUMBER_EXIST, userServiceException.getErrorCode());
	}

	@Test
	@DisplayName("회원가입 실패 - 닉네임 중복")
	void failRegisterUserAlreadyExistNickName() {
		//given
		given(userRepository.findByNickname(any()))
			.willReturn(Optional.ofNullable(User.builder()
				.userId("17sunsik")
				.password("1234")
				.username("ss")
				.mobileNumber("01033538090")
				.nickname("nj2")
				.email("17sunsik@gmail.com")
				.build()));
		//when
		UserServiceException userServiceException = assertThrows(UserServiceException.class,
			() -> userRegisterService.join(new RegisterUser.Request(
				"17sunsik",
				"1234",
				"nj2",
				"nss",
				"01033538090",
				"17sunsik@gmail.com"
			)));
		//then
		assertEquals(ErrorCode.ALREADY_NICKNAME_EXIST, userServiceException.getErrorCode());
	}
}
