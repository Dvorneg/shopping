package ru.den.shopping.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.den.shopping.dto.FamilyDTO;
import ru.den.shopping.model.Family;
import ru.den.shopping.model.Role;
import ru.den.shopping.model.User;
import ru.den.shopping.service.FamilyService;
import ru.den.shopping.service.ShoppingService;
import ru.den.shopping.service.UserService;

import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/family")
public class FamilyController {

    private final FamilyService familyService;
    private final ShoppingService shoppingService;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public FamilyController(FamilyService familyService, ShoppingService shoppingService, ModelMapper modelMapper, UserService userService) {
        this.familyService = familyService;
        this.shoppingService = shoppingService;
        this.modelMapper = modelMapper;

        this.userService = userService;
    }

    @GetMapping()
    public String getAll(Model model, @RequestParam(value = "id", required = false) Integer id) {

        List<Family> families= familyService.getAllFamilyByUser(userService.getAuthUser() );

        if (id != null) {
            return "redirect:/family/" + id;
        }

        if (families.size()==1)
            return "redirect:/family/"+families.get(0).getId();

        model.addAttribute("families", families);
        model.addAttribute("shopping", shoppingService.getAllShopping());
        model.addAttribute("user", new User(1, "Вася", Collections.emptyList(), "123", Collections.singleton(Role.USER)));
        log.info("FamilyController getAll");
        return "/family/families";
    }

    @GetMapping("/{id}")
    public String getFamilyById(Model model, @PathVariable int id) {
        Family family = familyService.getFamily(id);
        model.addAttribute("familyId", id);
        model.addAttribute("buy", family);
        model.addAttribute("shopping", shoppingService.getAllShoppingByOwner(family));
        model.addAttribute("familiesSize", familyService.getAllFamily().size());
        log.info("get all shopping by family");
        return "/shopping/list";
    }

    //start new
    @GetMapping("/new")
    public String newFamily(
            @ModelAttribute("family") FamilyDTO familyDTO) {
        log.info("add new family");
        return "/family/new";
    }

    //after new
    @PostMapping()
    public String create(@ModelAttribute("buy") @Valid FamilyDTO familyDTO, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "/shopping/new";
        }
        Family family = convertToFamily(familyDTO);
        familyService.save(family);
        return "redirect:/family";
    }

    //start edit
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        log.info("Редактирование семьи {}", id);
        model.addAttribute("family", familyService.getFamily(id));
        return "/family/edit";
    }

    //after edit
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("buy") @Valid Family family,
                         BindingResult bindingResult, @PathVariable("id") Integer id) {

        log.info("update familyId= {}", id);
        if (bindingResult.hasErrors())
            return "/edit";

        familyService.update(id, family);
        return "redirect:/family/list";
    }


    //page delete
    @GetMapping("/{id}/delete")
    public String deleteFamily(Model model, @ModelAttribute("family") FamilyDTO familyDTO,@PathVariable("id") Integer id) {
        model.addAttribute("family", familyService.getFamily(id));
        log.info("start deleting");
        return "/family/delete";
    }

    //request  delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        log.info("start deleting id=" + id);
        familyService.delete(id);
        return "redirect:/family/list";
    }

    //list of family
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("families", familyService.getAllFamily());
        log.info("/family/list");
        return "/family/list";
    }

    private Family convertToFamily(FamilyDTO familyDTO) {
        return modelMapper.map(familyDTO, Family.class);
    }

}
