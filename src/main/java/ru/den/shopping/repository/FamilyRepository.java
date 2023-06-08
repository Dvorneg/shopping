package ru.den.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.den.shopping.model.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Integer> {
}
