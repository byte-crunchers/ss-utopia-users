package com.utopia.user.bootstrap;

import com.utopia.user.entity.Users;
import com.utopia.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.time.LocalDate;

import java.util.Date;

@Profile("!test")
@Component
@RequiredArgsConstructor
public class H2DataBootstrap implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    String sDate1="12/2025";
    String expDate = "11/25"; // for example
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
    Date dueDate;


    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count()==0){

        	Calendar c = Calendar.getInstance();
        	c.setTime(new Date()); 
        	c.add(Calendar.DATE, 30);
        	dueDate = c.getTime();
        	
            loadUser();
        }
    }

    private void loadUser(){

        var User1 = Users.builder()
                .id(1L)
                .username("dan")
                .first_name("Dan")
                .last_name("Wo")
                .street_address( "529-5103 Hendrerit. Rd.")
                .email("smoothstack@email.com")
                .city("Miami Beach")
                .state("FL")
                .zip(33139)
                .phone(7778889999L)
                .active(true)
                .confirmed(true)
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .build();
        userRepository.save(User1);

        
        var User2 = Users.builder()
                .id(2L)
                .username("admin")
                .first_name("Tim")
                .last_name("lo")
                .street_address("Ap #281-2005 Tristique St.")
                .email("smoothstack@email.com")
                .zip(123)
                .phone(5556667777L)
                .active(true)
                .confirmed(true)
                .is_admin(true)
                .password(passwordEncoder.encode("admin123"))
                .build();

        userRepository.save(User2);

        var User3 = Users.builder()
                .id(3L)
                .username("manager")
                .first_name("Amy")
                .last_name("To")
                .email("smoothstack@email.com")
                .zip(1234)
                .phone(5556667777L)
                .active(true)
                .confirmed(true)
                .is_admin(false)
                .street_address("436-6810 Nunc Rd.")
                .password(passwordEncoder.encode("manager123"))
                .build();

        userRepository.save(User3);
        
        
        var User12 = Users.builder()
                .id(4L)
                .username("Lisandra")
                .password(passwordEncoder.encode("dan123"))
                .first_name("Boyle")
                .last_name("Will")
                .email("smoothstack@email.com")
                .street_address("338-1607 Egestas. St.")
                .city("New York")
                .state("NY")
                .zip(2345)
//                .ssn(123456789L)
                .dob(LocalDate.now().minusYears(20))
                .phone(5556667777L)
                .active(true)
                .confirmed(true)
                .is_admin(false)
                .build();

        userRepository.save(User12);


    }


}
