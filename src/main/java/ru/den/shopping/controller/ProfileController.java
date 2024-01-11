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

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
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
        System.out.println("------>.>>perred");
        model.addAttribute("user", new User());
        return "/user/registration";
    }

    @PostMapping("/registration")
    public String perfomRegistrationPage(@ModelAttribute("person") @Valid User user, BindingResult bindingResult) {
        //personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "/user/registration";

        userService.register(user);
        return "redirect:/auth/login";
    }

}
