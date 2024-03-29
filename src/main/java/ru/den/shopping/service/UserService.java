package ru.den.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.den.shopping.model.Family;
import ru.den.shopping.model.Role;
import ru.den.shopping.model.User;
import ru.den.shopping.repository.FamilyRepository;
import ru.den.shopping.repository.UserRepository;
import ru.den.shopping.security.SecurityUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FamilyRepository familyRepository;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, FamilyRepository familyRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.familyRepository = familyRepository;
    }

    public User get(Integer userId){
        return userRepository.getReferenceById(userId);
    }

    @Transactional
    public void register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(Role.USER));
        userRepository.save(user);
    }

    public List<User> findAllByNameContainingIgnoreCase(String name){
        return userRepository.findAllByNameContainingIgnoreCase(name);
    }

    public Optional<User> findByName(String name){
        return userRepository.findByName(name);
    }

    @Transactional  //NOT (readOnly = true)!
    public void addFamilyForUser(User user, Integer familyId){

        List<Family> allFamily = userRepository.getAllFamilyByUserId(user.getId());
        if (!allFamily.contains(familyRepository.getReferenceById(familyId)))
        {
            allFamily.add(familyRepository.getReferenceById(familyId));
        }
        user.setFamilies(allFamily);
        userRepository.save(user);
    }

    @Transactional
    public void deleteFamilyForUser(User user, Integer familyId){

        List<Family> allFamily = userRepository.getAllFamilyByUserId(user.getId());
        allFamily.remove(familyRepository.getReferenceById(familyId));
        user.setFamilies(allFamily);
        userRepository.save(user);
    }

    public User getAuthUser(){
        return SecurityUtil.getLoggedUser();
    }

}
