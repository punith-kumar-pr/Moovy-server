package com.moovy.service.impls;

import com.moovy.dto.UserRegistrationDto;
import com.moovy.entity.User;
import com.moovy.repository.UserRepository;
import com.moovy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserRegistrationDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPassword());
        return userRepository.save(user);
    }
}
