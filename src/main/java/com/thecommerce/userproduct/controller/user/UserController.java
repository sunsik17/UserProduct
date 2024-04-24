package com.thecommerce.userproduct.controller.user;

import com.thecommerce.userproduct.domain.user.dto.RegisterUser;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@PostMapping("/join")
	public ResponseEntity<RegisterUser.Response> register(
		@RequestBody @Valid RegisterUser.Request request) {

		return ResponseEntity.ok(
			RegisterUser.Response.builder().build()
		);
	}
}
