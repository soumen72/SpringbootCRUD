package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;


@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public int createUser(User user){

        SecureRandom secureRandom = new SecureRandom();
        byte [] salt = new byte[16];

        secureRandom.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        //mapping
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        //insert
        int id = userMapper.insert(new User(null, user.getUsername(), encodedSalt,hashedPassword, user.getFirstName(), user.getLastName()));


        return id;
    }

    //used by signupcontroller
    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    //get user
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

}
