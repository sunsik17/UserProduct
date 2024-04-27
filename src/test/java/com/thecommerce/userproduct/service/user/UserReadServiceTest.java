package com.thecommerce.userproduct.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.thecommerce.userproduct.domain.user.dto.UserDto;
import com.thecommerce.userproduct.domain.user.entity.User;
import com.thecommerce.userproduct.domain.user.repository.UserRepository;
import com.thecommerce.userproduct.util.PageResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
public class UserReadServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserReadService userReadService;

	@Test
	@DisplayName("유저 목록 조회 - 마지막 페이지")
	void getUsersHasNextTrue() {
		//given
		List<User> users = createUserList();
		given(userRepository.findAll(any(Pageable.class)))
			.willReturn(new PageImpl<>(users));
		given(userRepository.existsByIdGreaterThan(anyLong()))
			.willReturn(false);
		//when
		PageResult<UserDto> result = userReadService.getUsers(
			PageRequest.of(0, 10, Sort.by("createdAt")));
		//then
		assertFalse(result.isHasNext());
		assertEquals(result.getBody().size(), 10);
		assertEquals(result.getBody().get(0).getUserId(), "nss0");
		assertEquals(result.getBody().get(0).getUsername(), "sunsik0");
		assertEquals(result.getBody().get(0).getNickname(), "nj0");
		assertEquals(result.getBody().get(0).getMobileNumber(), "0100");
		assertEquals(result.getBody().get(0).getEmail(), "ss@gmail.com0");
		assertEquals(result.getBody().get(9).getUserId(), "nss9");
		assertEquals(result.getBody().get(9).getUsername(), "sunsik9");
		assertEquals(result.getBody().get(9).getNickname(), "nj9");
		assertEquals(result.getBody().get(9).getMobileNumber(), "0109");
		assertEquals(result.getBody().get(9).getEmail(), "ss@gmail.com9");
	}

	@Test
	@DisplayName("유저 목록 조회 - 다음 페이지 존재")
	void getUsersHasNextFalse() {
		//given
		List<User> users = createUserList();
		given(userRepository.findAll(any(Pageable.class)))
			.willReturn(new PageImpl<>(users.subList(0, 5)));
		given(userRepository.existsByIdGreaterThan(anyLong()))
			.willReturn(true);

		//when
		PageResult<UserDto> result = userReadService.getUsers(
			PageRequest.of(0, 5, Sort.by("createdAt"))
		);
		//then
		assertTrue(result.isHasNext());
		assertEquals(result.getBody().size(), 5);
		assertEquals(result.getBody().get(0).getUserId(), "nss0");
		assertEquals(result.getBody().get(0).getUsername(), "sunsik0");
		assertEquals(result.getBody().get(0).getNickname(), "nj0");
		assertEquals(result.getBody().get(0).getMobileNumber(), "0100");
		assertEquals(result.getBody().get(0).getEmail(), "ss@gmail.com0");
		assertEquals(result.getBody().get(4).getUserId(), "nss4");
		assertEquals(result.getBody().get(4).getUsername(), "sunsik4");
		assertEquals(result.getBody().get(4).getNickname(), "nj4");
		assertEquals(result.getBody().get(4).getMobileNumber(), "0104");
		assertEquals(result.getBody().get(4).getEmail(), "ss@gmail.com4");
	}

	private List<User> createUserList() {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			users.add(
				User.builder()
					.id((long) i)
					.userId("nss" + i)
					.password("1234")
					.username("sunsik" + i)
					.email("ss@gmail.com" + i)
					.nickname("nj" + i)
					.mobileNumber("010" + i)
					.build()
			);
		}
		return users;
	}
}
