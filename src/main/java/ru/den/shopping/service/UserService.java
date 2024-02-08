package ru.den.shopping.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.den.shopping.model.Role;
import ru.den.shopping.model.User;
import ru.den.shopping.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User get(Integer userId){
        return userRepository.getReferenceById(userId);
    }

    @Transactional
    public void register(User user){

        //кодиров
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(Role.USER));
        userRepository.save(user);
    }

    public List<User> findAllByNameContainingIgnoreCase(String name){
        return userRepository.findAllByNameContainingIgnoreCase(name);
    }


}
