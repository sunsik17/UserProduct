package com.thecommerce.userproduct.controller.test;

import com.thecommerce.userproduct.domain.user.entity.User;
import com.thecommerce.userproduct.domain.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

	private final UserRepository userRepository;

	@PostMapping("/test")
	public void create() {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			users.add(User.builder()
				.userId("sunsik" + i)
				.username(i + "")
				.nickname("nick" + i)
				.mobileNumber("010" + i)
				.email("ss@ss.com" + i)
				.password("1234")
				.build());
		}
		userRepository.saveAll(users);
	}

}
