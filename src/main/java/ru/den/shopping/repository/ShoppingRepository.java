package ru.den.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.den.shopping.model.Family;
import ru.den.shopping.model.Shopping;

import java.util.List;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping,Integer> {

    List<Shopping> getAllByOwner(Family family);

}
