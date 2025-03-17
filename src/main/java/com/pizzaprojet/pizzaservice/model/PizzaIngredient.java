package com.pizzaprojet.pizzaservice.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "pizza_ingredients")
public class PizzaIngredient {
    @EmbeddedId
    private PizzaIngredientId id = new PizzaIngredientId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pizzaId")
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @Column(name = "ingredient_id", insertable = false, updatable = false)
    private Long ingredientId;

    // Constructeurs
    public PizzaIngredient() {}

    public PizzaIngredient(Pizza pizza, Long ingredientId) {
        this.pizza = pizza;
        this.ingredientId = ingredientId;
        this.id.setPizzaId(pizza.getId());
        this.id.setIngredientId(ingredientId);
    }
}