package ru.den.shopping.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.den.shopping.model.User;
import ru.den.shopping.service.UserService;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors())
            return;

        User user = (User) target;

        if (!userService.findAllByNameContainingIgnoreCase(user.getName()).isEmpty())
            errors.rejectValue("name", "" ,"Уже зарегестрирован пользователь с таким именем!");

    }
}
