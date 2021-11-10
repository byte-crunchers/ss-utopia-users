package com.utopia.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.utopia.user.service.UserService;
import com.utopia.user.dao.UserDAO;
import com.utopia.user.dto.User;
import com.utopia.user.dto.UserDto;
import com.utopia.user.entity.Users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
 
	private final UserService userService;
	
    @Autowired
    private UserDAO userRepo;
     
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
         
        userRepo.save(user);
         
        return "register_success";
    }
    

    //get all test users
    @GetMapping(value = "/userinfo", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userInfo = userService.getAllUsers();
        if (userInfo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userInfo);
    }

    //get 1 user by id
    @GetMapping(value = "/userinfo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserDto>> getUserById(@PathVariable Long id) {
        List<UserDto> userInfo = userService.getUserById(id);
        if (userInfo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userInfo);
    }


    // edit user information
	@PostMapping(value = "/userinfo", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> updateUser(@RequestBody UserDto userInfo) {
		
		System.out.println("Update user info:");
		userInfo.printFields();
		
		String result = userService.updateUser(userInfo);
		if(result.isBlank())
			return ResponseEntity.noContent().build();  //status code 204
		else
			return ResponseEntity.unprocessableEntity().body(result);  //status code 422 with message
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}