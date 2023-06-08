package ru.den.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.den.shopping.model.Family;
import ru.den.shopping.repository.FamilyRepository;

import java.util.List;

@Service
@Transactional
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

}