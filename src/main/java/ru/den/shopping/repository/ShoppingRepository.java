package ru.den.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.den.shopping.model.Shopping;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping,Integer> {
}
