package ru.den.shopping.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.den.shopping.model.User;
import ru.den.shopping.service.FamilyService;
import ru.den.shopping.service.ShoppingService;

import java.util.Collections;

@Slf4j
@Controller
@RequestMapping("/family")
public class FamilyController {

    private final FamilyService familyService;
    private final ShoppingService shoppingService;
    private final ModelMapper modelMapper;

    public FamilyController(FamilyService familyService, ShoppingService shoppingService, ModelMapper modelMapper) {
        this.familyService = familyService;
        this.shoppingService = shoppingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String getAll(Model model,
                         @RequestParam(value = "sort_by_data", required = false) boolean sort_by_data) {
        model.addAttribute("famillies", familyService.getAllFamily());
        model.addAttribute("shopping", shoppingService.getAllShopping());
        model.addAttribute("user", new User(1, "Вася", Collections.emptyList()));
        log.info("FamilyController getAll");
        return "/family/families";
    }

}
