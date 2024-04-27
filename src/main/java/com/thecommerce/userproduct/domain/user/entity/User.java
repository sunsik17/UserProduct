package com.thecommerce.userproduct.domain.user.entity;

import static com.thecommerce.userproduct.exception.contants.ErrorCode.NEED_TO_CHECK_THE_NICKNAME_LENGTH;

import com.thecommerce.userproduct.domain.BaseEntity;
import com.thecommerce.userproduct.exception.UserServiceException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String userId;
	@NotNull
	private String password;
	@Column(unique = true, nullable = false)
	private String nickname;
	@NotNull
	private String username;
	@Column(unique = true, nullable = false)
	private String mobileNumber;
	@Email
	@Column(unique = true, nullable = false)
	private String email;

	public void update(String newNickname, String newMobileNum, String newEmail) {
		validateChangeNickname(newNickname);
		this.nickname = newNickname == null ? nickname : newNickname;
		this.mobileNumber = newMobileNum == null ? mobileNumber : newMobileNum;
		this.email = newEmail == null ? email : newEmail;
	}

	private void validateChangeNickname(String nickname) {
		if (nickname == null) return;
		if (nickname.length() < 1 || nickname.length() > 6) {
			throw new UserServiceException(NEED_TO_CHECK_THE_NICKNAME_LENGTH);
		}
	}
}
