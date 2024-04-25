package com.thecommerce.userproduct.domain.user.entity;

import com.thecommerce.userproduct.domain.BaseEntity;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
	private String userId;
	private String password;
	private String nickname;
	private String username;
	private String mobileNumber;
	@Email
	private String email;
}
