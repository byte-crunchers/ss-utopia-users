package com.utopia.user.dao;

import com.utopia.user.dto.UserDto;
import com.utopia.user.entity.Users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserRepository extends JpaRepository<Users, Long> {

    @Query(
            "select new com.utopia.user.dto.UserDto" +
                    "( u.id, u.username, u.first_name, u.last_name, u.email, u.street_address, u.city, u.state, "
                    + "u.zip, u.phone, u.ssn, u.dob, u.is_admin, u.active, u.confirmed ) " +
                    "from Users u"
    )
    List<UserDto> getAllUsers();
    

    @Query(
            "select new com.utopia.user.dto.UserDto" +
                    "( u.id, u.username, u.first_name, u.last_name, u.email, u.street_address, u.city, u.state, "
                    + "u.zip, u.phone, u.ssn, u.dob, u.is_admin, u.active, u.confirmed ) " +
                    "from Users u where u.id = ?1"
    )
    List<UserDto> getUserById(Long id);
    
    List<Users> findByUsername(String username);
    List<Users> findByEmail(String email);
}

