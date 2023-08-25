package ru.den.shopping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {

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


}
