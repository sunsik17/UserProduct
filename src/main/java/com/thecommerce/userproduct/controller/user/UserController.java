package com.thecommerce.userproduct.controller.user;

import com.thecommerce.userproduct.domain.user.dto.RegisterUser;
import com.thecommerce.userproduct.service.user.UserRegisterService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserRegisterService userRegisterService;


	@PostMapping("/join")
	public ResponseEntity<RegisterUser.Response> register(
		@RequestBody @Valid RegisterUser.Request request) {

		return ResponseEntity.status(HttpStatus.CREATED).body(
			RegisterUser.Response.from(userRegisterService.join(request))
		);
	}
}
