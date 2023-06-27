package ru.den.shopping.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.den.shopping.dto.ShoppingDTO;
import ru.den.shopping.model.Shopping;
import ru.den.shopping.service.FamilyService;
import ru.den.shopping.service.ShoppingService;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    private final ShoppingService shoppingService;
    private final FamilyService familyService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShoppingController(ShoppingService shoppingService, FamilyService familyService, ModelMapper modelMapper) {
        this.shoppingService = shoppingService;
        this.familyService = familyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String getAll(Model model
// ,@RequestParam(value = "sort_by_data", required = false) boolean sort_by_data
    ) {
        model.addAttribute("shopping", shoppingService.getAllShopping());
        log.info("getAll");
        return "/shopping";
        //return "books/index";
    }

    @GetMapping("/{id}")
    public String getShoppingById(Model model, @PathVariable Integer id, @RequestParam(value = "familyId", required = false) Integer familyId) {
        model.addAttribute("buy", shoppingService.getShopping(id));
        model.addAttribute("familyId", familyId);
        log.info("getShoppingById, familyId="+ familyId);
        return "/shopping/show";
    }

    //start edit
    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") int id, @RequestParam(value = "familyId", required = false) Integer familyId){

        model.addAttribute("familyId", familyId);
        log.info("вернёмся к семье {}", familyId);
        model.addAttribute("buy", shoppingService.getShopping(id));
        return "/shopping/edit";
    }

    //after edit
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("buy") @Valid ShoppingDTO shoppingDTO,
                         BindingResult bindingResult,
                         @PathVariable("id") int id, @RequestParam(value = "familyId", required = false) Integer familyId) {

        log.info("update familyId= {}", familyId);
        if (bindingResult.hasErrors())
            return "/edit";
        Shopping shopping = convertToShopping(shoppingDTO);
        shopping.setOwner(familyService.getFamily(familyId));
        shoppingService.update(id, shopping);
        return "redirect:/family/"+shopping.getOwner().getId();
    }

    //start new
    @GetMapping("/new")
    public String newBook(Model model, @ModelAttribute("buy") ShoppingDTO shoppingDTO,
                          @RequestParam(value = "familyId", required = false) Integer familyId) {
        model.addAttribute("familyId", familyId);
        return "/shopping/new";
    }

    //after new
    @PostMapping()
    public String create(@ModelAttribute("buy") @Valid ShoppingDTO shoppingDTO,
                         BindingResult bindingResult, @RequestParam(value = "familyId", required = false) Integer familyId) {

        if (bindingResult.hasErrors()) {
            return "/shopping/new";
        }
        Shopping shopping = convertToShopping(shoppingDTO);
        shopping.setOwner(familyService.getFamily(familyId));
        shoppingService.save(shopping);
        //return "redirect:/shopping";
        return "redirect:/family/"+shopping.getOwner().getId();
    }

    @DeleteMapping ("/{id}")
    public String delete(@PathVariable("id") int id) {
        Integer familyId = shoppingService.getShopping(id).getOwner().getId();
        shoppingService.delete(id);
        return "redirect:/family/"+familyId;
    }

    private Shopping convertToShopping(ShoppingDTO shoppingDTO) {
        Shopping shopping = modelMapper.map(shoppingDTO, Shopping.class);
            //String Sensor find in Repository
        shopping.setData(LocalDateTime.now());
        return shopping;
    }
}
