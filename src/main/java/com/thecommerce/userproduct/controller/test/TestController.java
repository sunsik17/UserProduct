package com.thecommerce.userproduct.controller.test;

import com.thecommerce.userproduct.domain.user.entity.User;
import com.thecommerce.userproduct.domain.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "테스트를 위한 유저 생성 api")
@RestController
@RequiredArgsConstructor
public class TestController {

	private final UserRepository userRepository;

	@PostMapping("/create-users")
	public void create() {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			users.add(User.builder()
				.userId("nss" + i)
				.username("sunsik" + i)
				.nickname("nick" + i)
				.mobileNumber("010" + i)
				.email("ss@ss.com" + i)
				.password("1234")
				.build());
		}
		userRepository.saveAll(users);
	}

}
