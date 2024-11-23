package com.restproject;


import com.restproject.users.UserRepository;
import com.restproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public void createAccount() {

        if (userRepository.existsByFaketckimlikno(121221L)){

        }
    }

    @Override
    public Boolean existsByFaketckimlikno(Long faketckimlikno) {
        return userRepository.existsByFaketckimlikno(faketckimlikno);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByMusterino(Long musteri_no) {
        return userRepository.findByMusterino(musteri_no);
    }

    @Override
    public Optional<User> findByFaketckimlikno(Long faketckimlikno) {
        return userRepository.findByFaketckimlikno(faketckimlikno);
    }

    @Override
    public List<User> findAll(){
       return userRepository.findAll();
    }

}
