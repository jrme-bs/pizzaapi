package com.pizzaprojet.pizzaservice.repository;

import com.pizzaprojet.pizzaservice.model.PizzaIngredient;
import com.pizzaprojet.pizzaservice.model.PizzaIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PizzaIngredientRepository extends JpaRepository<PizzaIngredient, PizzaIngredientId> {
    List<PizzaIngredient> findByPizzaId(Long pizzaId);
    void deleteByPizzaId(Long pizzaId);
}