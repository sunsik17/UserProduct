package com.thecommerce.userproduct.domain.user.repository;

import com.thecommerce.userproduct.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	Optional<User> findByMobileNumber(String mobileNumber);
	Optional<User> findByUserId(String userId);
	Optional<User> findByNickname(String nickname);
	Page<User> findAll(Pageable pageable);
	boolean existsByIdGreaterThan(Long id);
}
