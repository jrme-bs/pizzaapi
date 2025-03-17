package com.pizzaprojet.pizzaservice.repository;

import com.pizzaprojet.pizzaservice.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    boolean existsByNom(String nom);
}