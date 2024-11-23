package com.restproject;


import com.restproject.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createAccount();
    Boolean existsByMusterino(Long musterino);
    Boolean existsByFaketckimlikno(Long faketckimlikno);
    User save(User user);
    Optional<User> findByMusterino(Long musterino);
    Optional<User> findByFaketckimlikno(Long faketckimlikno);
    List<User> findAll();
    void deleteByMusterino(Long musterino);
}
