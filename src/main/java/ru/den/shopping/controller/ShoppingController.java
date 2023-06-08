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
import ru.den.shopping.service.ShoppingService;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    private final ShoppingService shoppingService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShoppingController(ShoppingService shoppingService, ModelMapper modelMapper) {
        this.shoppingService = shoppingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String getAll(Model model,
                        @RequestParam(value = "sort_by_data", required = false) boolean sort_by_data) {
        model.addAttribute("shopping", shoppingService.getAllShopping());
        log.info("getAll");
        return "/shopping";
        //return "books/index";
    }

    @GetMapping("/{id}")
    public String getShoppingById(Model model, @PathVariable int id) {
        model.addAttribute("buy", shoppingService.getShopping(id));
        log.info("getShoppingById");
        return "/show";
    }


    //start edit
    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") int id){
        model.addAttribute("buy", shoppingService.getShopping(id));
        return "/edit";
    }

    //after edit
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("buy") @Valid ShoppingDTO shoppingDTO,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        log.info("update");
        if (bindingResult.hasErrors())
            return "/edit";
        Shopping shopping = convertToShopping(shoppingDTO);
        shoppingService.update(id, shopping);
        return "redirect:/shopping";
    }

    //start new
    @GetMapping("/new")
    public String newBook(@ModelAttribute("buy") ShoppingDTO shoppingDTO){
        return "/new";
    }

    //after new
    @PostMapping()
    public String create(@ModelAttribute("buy") @Valid ShoppingDTO shoppingDTO,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new";
        }
        Shopping shopping = convertToShopping(shoppingDTO);
        shoppingService.save(shopping);
        return "redirect:/shopping";
    }

    @DeleteMapping ("/{id}")
    public String delete(@PathVariable("id") int id) {
        shoppingService.delete(id);
        return "redirect:/shopping";
    }

    private Shopping convertToShopping(ShoppingDTO shoppingDTO) {
        Shopping shopping = modelMapper.map(shoppingDTO, Shopping.class);
            //String Sensor find in Repository
        shopping.setData(LocalDateTime.now());
        return shopping;
    }
}
