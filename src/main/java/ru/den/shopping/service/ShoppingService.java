package ru.den.shopping.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.den.shopping.model.Family;
import ru.den.shopping.model.Shopping;
import ru.den.shopping.repository.ShoppingRepository;
import ru.den.shopping.util.ShoppingException;

import java.util.List;

@Service
@Transactional
public class ShoppingService {

    private final ShoppingRepository repository;

    public ShoppingService(ShoppingRepository repository) {
        this.repository = repository;
    }

    public Shopping getShopping(Integer shoppingId)
    {
        return repository.findById(shoppingId).orElseThrow(() -> new ShoppingException("Элемент не найден!"));
    }

    public List<Shopping> getAllShoppingByOwner(Family family)
    {
        return repository.getAllByOwner(family);
    }

    public List<Shopping> getAllShopping()
    {
        return repository.findAll();
    }

    @Transactional
    public void update(int id, Shopping updatedShopping){
        updatedShopping.setId(id);
        repository.save(updatedShopping);
    }

    @Transactional
    public void save(Shopping shopping){
        repository.save(shopping);
    }

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }

}
