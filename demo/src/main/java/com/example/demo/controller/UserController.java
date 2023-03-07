package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/users")
public class UserController {
	final UserService userService;

	@Operation(description = "Create a user")
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody UserDto user) {
		return ResponseEntity.ok(this.userService.create(user));
	}
	@Operation(description = "update a user's information")
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody UserDto user) {
		return ResponseEntity.ok(this.userService.update(user));
	}
	@Operation(description = "inactive a user")
	@PutMapping("/inactive/{userEmail}")
	public ResponseEntity<User> deleteUser(@PathVariable ("userEmail") String userEmail){
		return ResponseEntity.ok(this.userService.inactive(userEmail));
	}
	@Operation(description = "Get all users' information")
	@GetMapping("/getall")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	@Operation(description = "Get a user's information by user Id")
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") long userId) {
		return ResponseEntity.ok(this.userService.findById(userId));
	}

	@Operation(description = "Get a user's information by user email")
	@GetMapping
	public ResponseEntity<User> getUserById(@RequestParam(value = "email") String userEmail) {
		return ResponseEntity.ok(this.userService.findByEmail(userEmail));
	}

}
