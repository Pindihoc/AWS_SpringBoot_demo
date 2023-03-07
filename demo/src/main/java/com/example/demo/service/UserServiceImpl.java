package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService{
    final UserRepository userRepository;
    @Override
    public User create(UserDto userDto) {
        if (StringUtils.isBlank(userDto.email())) {
            throw new ResourceNotFoundException("Email is required for creating new user.");
        }

        if (userRepository.findByEmail(userDto.email()).isPresent()) {
            throw new ResourceNotFoundException("Email is already existed. Please choose another email.");
        }

        User user = new User(userDto.firstName(), userDto.lastName(), userDto.email());
        return userRepository.save(user);
    }
    @Override
    public User update(UserDto user) {
        Optional<User> userOptional = userRepository.findByEmail(user.email());
        User existedUserInDB = userOptional.orElseThrow(
                () -> new ResourceNotFoundException("User not found with email :" + user.email()));

        existedUserInDB.setFirstName(user.firstName());
        existedUserInDB.setLastName(user.lastName());
        existedUserInDB.setEmail(user.email());

        return userRepository.save(existedUserInDB);
    }
    @Override
    public User inactive(String userEmail) {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        User existedUserInDB = userOptional.orElseThrow(
                () -> new ResourceNotFoundException("User not found with email :" + userEmail));
        existedUserInDB.setActiveState(Boolean.FALSE);

        return userRepository.save(existedUserInDB);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User findByEmail(String userEmail) {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        return userOptional.orElseThrow(() -> new ResourceNotFoundException("User not found with email :" + userEmail));
    }
    @Override
    public User findById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
    }

}
