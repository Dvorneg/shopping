package ru.den.shopping.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.den.shopping.dto.FamilyDTO;
import ru.den.shopping.dto.ShoppingDTO;
import ru.den.shopping.model.Family;
import ru.den.shopping.model.Shopping;
import ru.den.shopping.model.User;
import ru.den.shopping.service.FamilyService;
import ru.den.shopping.service.ShoppingService;

import java.time.LocalDateTime;
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
                         @RequestParam(value = "sort_by_data", required = false) boolean sort_by_data,
                        @RequestParam(value = "id", required = false) Integer id) {

        if(id!= null)
        {
            //refactoring
            return "redirect:/family/"+id;
        }

        model.addAttribute("families", familyService.getAllFamily());
        model.addAttribute("shopping", shoppingService.getAllShopping());
        model.addAttribute("user", new User(1, "Вася", Collections.emptyList()));
        log.info("FamilyController getAll");
        return "/family/families";
    }

    @GetMapping("/{id}")
    public String getFamilyById(Model model, @PathVariable int id) {
        Family family = familyService.getFamily(id);
        model.addAttribute("familyId", id);
        model.addAttribute("buy", family);
        model.addAttribute("shopping", shoppingService.getAllShoppingByOwner(family));
        log.info("get all shopping by family");
        return "/family/show";
    }

    //start new
    @GetMapping("/new")
    public String newFamily(Model model, @ModelAttribute("family") FamilyDTO familyDTO
    //                      ,@RequestParam(value = "familyId", required = false) Integer familyId
    ) {
        //model.addAttribute("familyId", familyId);
        log.info("add new family");
        return "/family/new";
    }

    //after new
    @PostMapping()
    public String create(@ModelAttribute("buy") @Valid FamilyDTO familyDTO,
                         BindingResult bindingResult, @RequestParam(value = "familyId", required = false) Integer familyId) {

        if (bindingResult.hasErrors()) {
            return "/shopping/new";
        }
        Family family = convertToFamily(familyDTO);
        //family.setOwner(familyService.getFamily(familyId));
        familyService.save(family);
        //return "redirect:/shopping";
        return "redirect:/family";
    }

    private Family convertToFamily(FamilyDTO familyDTO) {
        Family family = modelMapper.map(familyDTO, Family.class);
        //family.setData(LocalDateTime.now());
        return family;
    }

}
