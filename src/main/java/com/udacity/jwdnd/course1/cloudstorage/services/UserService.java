package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUserNameAvailable(String userName){
        if (getUser(userName)==null){
            return true;
        }else
            return false;
    }

    public int createUser(Users users){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(users.getPassword(), encodedSalt);
        return userMapper.insertUsers(new Users(null,users.getUserName(),encodedSalt,hashedPassword,users.getFirstName(),users.getLastName()));
    }
    public Users getUser(String userName){
        return userMapper.getUser(userName);
    }

    public void deleteUser(String userName){
    userMapper.deleteUser(userName);
    }

}
