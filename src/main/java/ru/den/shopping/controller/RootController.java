package ru.den.shopping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class RootController {

    @GetMapping()
    public String getAll() {
        log.info("RootController");
        return "redirect:/family";
    }

    @GetMapping("/logout")
    // @ResponseBody
    public String logout() {
        log.info("logout страница");
        return "/user/logout";
    }

}
