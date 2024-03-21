package ru.den.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.den.shopping.model.Family;
import ru.den.shopping.model.User;
import ru.den.shopping.repository.FamilyRepository;
import ru.den.shopping.util.ShoppingException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FamilyService {

    private final FamilyRepository familyRepository;

    @Autowired
    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public List<Family> getAllFamily()
    {
        return familyRepository.findAll();
    }

    public Family getFamily(Integer familyId)
    {
        return familyRepository.findById(familyId).orElseThrow(() -> new ShoppingException("Элемент не найден!"));
    }
    @Transactional
    public void save(Family family){
        familyRepository.save(family);
    }

    @Transactional
    public void delete(int id){
        familyRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Family updatedFamily){
        updatedFamily.setId(id);
        familyRepository.save(updatedFamily);
    }

    public List<Family> getAllFamilyByUser(User user)
    {
        return familyRepository.findAllByUser(user);
    }

}
