package ru.den.shopping.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.den.shopping.model.Family;
import ru.den.shopping.model.Role;
import ru.den.shopping.model.Shopping;
import ru.den.shopping.model.User;
import ru.den.shopping.repository.ShoppingRepository;

import java.time.LocalDateTime;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
@Transactional
//@Sql("classpath*:data.sql")
class ShoppingServiceTest {

    @Mock
    private ShoppingRepository shoppingRepository;

    @InjectMocks
    private ShoppingService shoppingService;

    @Test
    public void True(){
    }

    @Test
    public void getShopping()
    {
        Integer shoppingId=1;

        User user = new User(1, "Коля",Collections.emptyList(),"123", Collections.singleton(Role.USER));
        Family  owner = new Family(1, "Петровы");
        Shopping testShopping = new Shopping(shoppingId, "пакет для пакетов", LocalDateTime.of(2004, 10, 19, 10, 23, 54, 0), "100 гр","Шестёрочка", owner);
        Mockito.when(shoppingRepository.findById(shoppingId)).thenReturn(java.util.Optional.of(testShopping));
        Assertions.assertEquals(shoppingService.getShopping(shoppingId),testShopping);
    }

}