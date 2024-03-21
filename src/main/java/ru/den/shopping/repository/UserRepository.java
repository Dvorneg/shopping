package ru.den.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.den.shopping.model.Family;
import ru.den.shopping.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);

    List<User> findAllByNameContainingIgnoreCase(String name);

    // test
    @Query("SELECT  f FROM Family f LEFT JOIN FETCH f.user u where u.id = :userId")
    List<Family> getAllFamilyByUserId(Integer userId);
}
