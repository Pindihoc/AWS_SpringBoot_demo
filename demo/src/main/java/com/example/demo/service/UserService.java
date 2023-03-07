package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import java.util.List;

public interface UserService {

    User create(UserDto user);
    User update(UserDto user);
    User inactive(String userEmail);
    List<User> getAllUsers();
    User findById(long id);
    User findByEmail(String userEmail);
}
