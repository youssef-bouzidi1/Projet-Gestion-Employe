package com.hrms.employee.service;

import com.hrms.employee.model.Role;
import com.hrms.employee.model.User;
import com.hrms.employee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    @Transactional
    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists with this username.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getPosition().equalsIgnoreCase("HR")){
            user.setRole(Role.HR);
        } else {
            user.setRole(Role.EMPLOYEE);
        }
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        return user;
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = getUser(id);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPosition(userDetails.getPosition());
        user.setSalary(userDetails.getSalary());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            String hrUsername = "admin";
            Optional<User> existingUser = userRepository.findByUsername(hrUsername);
            if (existingUser.isEmpty()){
                User hrUser = new User();
                hrUser.setFirstName("Admin");
                hrUser.setLastName("Admin");
                hrUser.setEmail("admin@email.com");
                hrUser.setUsername(hrUsername);
                hrUser.setPassword(passwordEncoder.encode("admin"));
                hrUser.setRole(Role.HR);
                hrUser.setPosition("HR");
                hrUser.setContractType(com.hrms.employee.model.ContractType.CDI);
                hrUser.setJoiningDate(LocalDate.now());
                hrUser.setDepartmentId(1L);
                hrUser.setSalary(10000.0);
                userRepository.save(hrUser);
                System.out.println("Admin User Created");
            } else{
                System.out.println("Admin User Already Exists");
            }
        };
    }
}