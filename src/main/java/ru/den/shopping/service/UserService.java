package ru.den.shopping.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.den.shopping.model.User;
import ru.den.shopping.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(Integer userId){
        return userRepository.getReferenceById(userId);
    }



    @Transactional
    public void register(User user){
        //кодиров
        //person.setPassword(passwordEncoder.encode(person.getPassword()));
        //person.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
