package com.utopia.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.utopia.user.dao.UserDAO;
import com.utopia.user.dto.CustomUserDetails;
import com.utopia.user.dto.User;
 
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserDAO userRepo;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
 
}