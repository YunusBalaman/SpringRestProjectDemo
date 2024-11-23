package com.restproject.users;

import com.restproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByFaketckimlikno(Long faketckimlikno);
    Boolean existsByMusterino(Long musterino);
    Optional<User> findByMusterino(Long musterino);
    Optional<User> findByFaketckimlikno(Long faketckimlikno);
    void deleteByMusterino(Long musterino);
}