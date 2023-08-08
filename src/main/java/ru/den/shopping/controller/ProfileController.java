package ru.den.shopping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/logout")
    // @ResponseBody
    public String logout() {
        log.info("logout страница");
        return "/user/logout";
    }

}
