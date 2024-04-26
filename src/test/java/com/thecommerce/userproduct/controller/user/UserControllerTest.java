package com.thecommerce.userproduct.controller.user;

import static com.thecommerce.userproduct.exception.contants.ErrorCode.ALREADY_EMAIL_EXIST;
import static com.thecommerce.userproduct.exception.contants.ErrorCode.ALREADY_MOBILE_NUMBER_EXIST;
import static com.thecommerce.userproduct.exception.contants.ErrorCode.ALREADY_NICKNAME_EXIST;
import static com.thecommerce.userproduct.exception.contants.ErrorCode.ALREADY_USERID_EXIST;
import static com.thecommerce.userproduct.exception.contants.ErrorCode.NEED_TO_CHECK_THE_NICKNAME_LENGTH;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecommerce.userproduct.domain.user.dto.RegisterUser;
import com.thecommerce.userproduct.domain.user.dto.UpdateUser;
import com.thecommerce.userproduct.domain.user.dto.UserDto;
import com.thecommerce.userproduct.exception.UserServiceException;
import com.thecommerce.userproduct.service.user.UserRegisterService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(UserController.class)
public class UserControllerTest {

	@MockBean
	private UserRegisterService userRegisterService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	@DisplayName("회원가입 성공")
	void successCreateUser() throws Exception {
		//given
		LocalDateTime dateTime = LocalDateTime.now();
		given(userRegisterService.join(any()))
			.willReturn(UserDto.builder()
				.username("nss")
				.userId("17sunsik")
				.mobileNumber("01033538090")
				.email("17sunsik@gmail.com")
				.nickname("nj2")
				.createdAt(dateTime)
				.build());
		//when
		//then
		mockMvc.perform(post("/api/users/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(
					new RegisterUser.Request(
						"17sunsik",
						"1234",
						"nj2",
						"nss",
						"01033538090",
						"17sunsik@gmail.com"
					)
				)))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.userId").value("17sunsik"))
			.andExpect(jsonPath("$.nickname").value("nj2"))
			.andExpect(jsonPath("$.createdAt").value(dateTime.toString()))
			.andDo(print());
	}

	@Test
	@DisplayName("회원가입 실패 - 중복 아이디")
	void failedCreateUserAlreadyExistUserId() throws Exception {
		//given
		RegisterUser.Request request = new RegisterUser.Request(
			"17sunsik",
			"1234",
			"nj2",
			"nss",
			"01033538090",
			"17sunsik@gmail.com"
		);
		given(userRegisterService.join(any())).
			willThrow(new UserServiceException(ALREADY_USERID_EXIST));
		//when
		//then
		mockMvc.perform(post("/api/users/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(jsonPath("$.errorCode").value("ALREADY_USERID_EXIST"))
			.andDo(print());
	}

	@Test
	@DisplayName("회원가입 실패 - 중복 닉네임")
	void failedCreateUserAlreadyExistNickname() throws Exception {
		//given
		RegisterUser.Request request = new RegisterUser.Request(
			"17sunsik",
			"1234",
			"nj2",
			"nss",
			"01033538090",
			"17sunsik@gmail.com"
		);
		given(userRegisterService.join(any())).
			willThrow(new UserServiceException(ALREADY_NICKNAME_EXIST));
		//when
		//then
		mockMvc.perform(post("/api/users/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(jsonPath("$.errorCode").value("ALREADY_NICKNAME_EXIST"))
			.andDo(print());
	}

	@Test
	@DisplayName("회원가입 실패 - 중복 모바일 번호")
	void failedCreateUserAlreadyExistMobileNumber() throws Exception {
		//given
		RegisterUser.Request request = new RegisterUser.Request(
			"17sunsik",
			"1234",
			"nj2",
			"nss",
			"01033538090",
			"17sunsik@gmail.com"
		);
		given(userRegisterService.join(any())).
			willThrow(new UserServiceException(ALREADY_MOBILE_NUMBER_EXIST));
		//when
		//then
		mockMvc.perform(post("/api/users/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(jsonPath("$.errorCode").value("ALREADY_MOBILE_NUMBER_EXIST"))
			.andDo(print());
	}

	@Test
	@DisplayName("회원가입 실패 - 중복 이메일")
	void failedCreateUserAlreadyExistEmail() throws Exception {
		//given
		RegisterUser.Request request = new RegisterUser.Request(
			"17sunsik",
			"1234",
			"nj2",
			"nss",
			"01033538090",
			"17sunsik@gmail.com"
		);
		given(userRegisterService.join(any())).
			willThrow(new UserServiceException(ALREADY_EMAIL_EXIST));
		//when
		//then
		mockMvc.perform(post("/api/users/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(jsonPath("$.errorCode").value("ALREADY_EMAIL_EXIST"))
			.andDo(print());
	}

	@Test
	@DisplayName("회원 정보 수정 성공")
	void successUpdateUser() throws Exception {
		//given
		LocalDateTime dateTime = LocalDateTime.now();

		UpdateUser.Request request = UpdateUser.Request.builder()
			.nickname("new")
			.email("newEmail")
			.mobileNumber("01012345678")
			.build();

		given(userRegisterService.update(anyString(), any()))
			.willReturn(UserDto.builder()
				.username("nss")
				.userId("17sunsik")
				.mobileNumber("01012345678")
				.email("newEmail@ee.com")
				.nickname("new")
				.createdAt(dateTime)
				.updatedAt(dateTime)
				.build());
		//when
		//then
		mockMvc.perform(put("/api/users/17sunsik")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nickname").value("new"))
			.andExpect(jsonPath("$.mobile").value("01012345678"))
			.andExpect(jsonPath("$.email").value("newEmail@ee.com"))
			.andExpect(jsonPath("$.updatedAt").value(dateTime.toString()))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@DisplayName("회원정보 수정 실패 - 닉네임 글자 수 오류")
	void failedUpdateUserNickname() throws Exception {
		//given
		UpdateUser.Request request = UpdateUser.Request.builder()
			.nickname("newNickname")
			.email("newEmail@ee.com")
			.mobileNumber("01012345678")
			.build();

		given(userRegisterService.update(anyString(), any())).
			willThrow(new UserServiceException(NEED_TO_CHECK_THE_NICKNAME_LENGTH));
		//when
		//then
		mockMvc.perform(put("/api/users/17sunsik")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(jsonPath("$.errorCode").value("NEED_TO_CHECK_THE_NICKNAME_LENGTH"))
			.andDo(print());
	}

}
