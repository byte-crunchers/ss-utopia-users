package com.utopia.user.service;

import com.utopia.user.entity.Users;
import com.utopia.user.dao.UserRepository;
import com.utopia.user.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
    	return userRepository.getAllUsers();
    }

    @Override
    public List<UserDto> getUserById(Long id) {
    	return userRepository.getUserById(id);
    }

    // update user fields, submitted from 'admin edit user details' page.
    // validation: returns "" on success, otherwise returns error message.
    @Override
    public String updateUser(UserDto dto) {

		try {
			Users u = userRepository.findById(dto.getId()).orElse(null);

			//validate username is unique
			List<Users> list = userRepository.findByUsername(dto.getUsername());
			for(Users user : list) {
				if(user.getId() != u.getId())
					return "Duplicate username";
			}

			//validate email is unique
			List<Users> list2 = userRepository.findByEmail(dto.getEmail());
			for(Users user : list2) {
				if(user.getId() != u.getId())
					return "Duplicate email";
			}

			u.setUsername(dto.getUsername());
			u.setFirst_name(dto.getFirst_name());
			u.setLast_name(dto.getLast_name());
			u.setEmail(dto.getEmail());
			u.setStreet_address(dto.getStreet_address());
			u.setCity(dto.getCity());
			u.setState(dto.getUs_state());
			u.setSsn(dto.getSsn());
			
			//validate zipcode is 5 digits
			if(dto.getZip() > 0 && dto.getZip() <= 99999)
				u.setZip(dto.getZip());
			else
				return "Invalid zip code";
			
			//validate phone number
			if(dto.getPhone() <= 0)
				return "Invalid phone number";
			String p = dto.getPhone().toString();
			if(p.length() == 11)
				p = p.substring(1);
			if(p.length() == 10)
				u.setPhone(Long.parseLong(p));
			else
				return "Invalid phone number";
						
			//validate date of birth is in the past
			if(!dto.getDob().isAfter(LocalDate.now()))
				u.setDob(dto.getDob());
			else
				return "Invalid date of birth";
			
			u.setConfirmed(dto.getConfirmed());
			u.setIs_admin(dto.getIs_admin());
			u.setActive(dto.getActive());

			userRepository.save(u);
			return "";  //success
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "Update user failed!";
    }
    
}
