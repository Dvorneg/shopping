package ru.den.shopping.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.den.shopping.model.User;
import ru.den.shopping.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> person= userRepository.findByName(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found /Den");

        log.info("loadUserByUsername");
        return new UserDetailsImpl(person.get());

    }

}
