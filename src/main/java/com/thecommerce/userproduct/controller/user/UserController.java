package com.thecommerce.userproduct.controller.user;

import com.thecommerce.userproduct.domain.user.dto.RegisterUser;
import com.thecommerce.userproduct.domain.user.dto.UpdateUser;
import com.thecommerce.userproduct.service.user.UserRegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 등록 컨트롤러")
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

	@PutMapping("/{userId}")
	public ResponseEntity<UpdateUser.Response> updateRegister(
		@RequestBody UpdateUser.Request request,
		@PathVariable String userId) {

		return ResponseEntity.ok().body(
			UpdateUser.Response.from(userRegisterService.update(userId, request))
		);
	}
}
