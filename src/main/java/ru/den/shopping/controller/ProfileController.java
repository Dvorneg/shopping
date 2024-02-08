package ru.den.shopping.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.den.shopping.model.User;
import ru.den.shopping.service.UserService;
import ru.den.shopping.util.UserValidator;

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public ProfileController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String loginPage(){
        log.info("ProfileController: loginPage");
        return "/user/login";
    }

    @GetMapping("/")
    // @ResponseBody
    public String index() {
        log.info("стартовая страница");
        return "/family/list";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/user/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){ //2024 add model
        log.info("ProfileController: registrationPage");
        log.debug("------>.>>before");
        model.addAttribute("user", new User());
        return "/user/registration";
    }

    @PostMapping("/registration")
    public String perfomRegistrationPage(@ModelAttribute("user") @Valid User userToAdd, BindingResult bindingResult) {

        log.debug("------>.>>after");
        userValidator.validate(userToAdd,bindingResult);

        if (bindingResult.hasErrors())
            return "/user/registration";

        userService.register(userToAdd);
        return "redirect:/auth/login";
    }

}
