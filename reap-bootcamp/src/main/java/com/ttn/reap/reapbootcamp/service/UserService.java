package com.ttn.reap.reapbootcamp.service;

import com.ttn.reap.reapbootcamp.entity.User;
import com.ttn.reap.reapbootcamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveOnSignUp(User user)
    {
        userRepository.save(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
