package com.thecommerce.userproduct.service.user;

import com.thecommerce.userproduct.domain.user.dto.UserDto;
import com.thecommerce.userproduct.domain.user.entity.User;
import com.thecommerce.userproduct.domain.user.repository.UserRepository;
import com.thecommerce.userproduct.util.PageResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReadService {

	private final UserRepository userRepository;

	public PageResult<UserDto> getUsers(Pageable pageable) {
		List<User> users = userRepository.findAll(pageable).toList();

		Long nextKey = users.isEmpty() ? null :
			users.stream()
				.mapToLong(User::getId)
				.max().orElse(PageResult.NON_KEY);

		return new PageResult<>(
			users.stream()
				.map(UserDto::fromEntity)
				.collect(Collectors.toList()),
			hasNext(nextKey)
		);
	}

	private boolean hasNext(Long id) {
		if (id == null) {
			return false;
		}
		return userRepository.existsByIdGreaterThan(id);
	}
}
