package ru.den.shopping.controller;

import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.den.shopping.service.ShoppingService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ShoppingControllerTest {

    private final ShoppingService shoppingService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    public ShoppingControllerTest(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @Test
    void getAll() {
    }

    @Test
    @Disabled
    void getShoppingById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/shopping/1"))
                .andExpect(status().isOk())
                .andExpect(view().name(Matchers.equalTo("/show")));
                //.andExpect(model().attribute("buy").match(shoppingService.getShopping(1)));

    }

}