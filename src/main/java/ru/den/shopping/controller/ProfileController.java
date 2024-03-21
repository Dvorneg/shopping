package ru.den.shopping.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.den.shopping.model.Family;
import ru.den.shopping.model.User;
import ru.den.shopping.service.FamilyService;
import ru.den.shopping.service.UserService;
import ru.den.shopping.util.UserValidator;

import java.util.Collections;

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final FamilyService familyService;
    private final UserValidator userValidator;

    @Autowired
    public ProfileController(UserService userService, FamilyService familyService, UserValidator userValidator) {
        this.userService = userService;
        this.familyService = familyService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String loginPage(){
        log.info("ProfileController: loginPage");
        return "/user/login";
    }

    @GetMapping("/")
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

        //add default family
        Family family = new Family(userToAdd.getName()+"'s family");
        familyService.save (family);
        family.setUser(Collections.singletonList(userToAdd));
        userToAdd.setFamilies(Collections.singletonList(family));
        userService.register(userToAdd);

        return "redirect:/auth/login";
    }

    @GetMapping("/{id}/search")
    //public String search(@RequestParam(name = "user", defaultValue = "test") User user, Model model) {
    public String search(@RequestParam(name = "userName",required = false) String userName, Model model, @PathVariable("id") Integer familyId) {

        User user = userService.findByName(userName).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("familyId", familyId);
        model.addAttribute("userName", userName);
        log.info("/user/search before");
        return "/user/search";
    }

    //after search
    @PostMapping("/{id}/search")
    //public String postSearch(@ModelAttribute("userName") @Valid UserDTO userDTO, BindingResult bindingResult)
    public String postSearch(@ModelAttribute("userName") String userName, RedirectAttributes redirectAttributes, @PathVariable("id") Integer familyId)
    {

        log.info("/user/search:" +userName);
 /*       if (bindingResult.hasErrors()) {
            return "/profile/search";
        }*/

        //User user = userService.findByName(userName).orElse(new User());
        redirectAttributes.addAttribute("userName", userName);
        return "redirect:/profile/"+familyId+"/search";
    }

    //after search
    @PatchMapping ("/{id}/search")
    //public String patchSearch(@ModelAttribute("userName") @Valid UserDTO userDTO, BindingResult bindingResult)
    public String patchSearch(@RequestParam(name = "name") String userName, @PathVariable("id") Integer familyId)
    {

        log.info("Patch family id="+familyId+", for the user=" +userName);
        User user = userService.findByName(userName).get();
        userService.addFamilyForUser(user,familyId);
        return "redirect:/family/list";
    }

}
