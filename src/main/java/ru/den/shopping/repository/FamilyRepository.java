package ru.den.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.den.shopping.model.Family;
import ru.den.shopping.model.User;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Integer> {

    List<Family> findAllByUser(User user);

}
