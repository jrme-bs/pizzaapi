package com.pizzaprojet.pizzaservice.model;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class PizzaIngredientId implements Serializable {
    @Column(name = "pizza_id")
    private Long pizzaId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    // Constructeurs
    public PizzaIngredientId() {}

    public PizzaIngredientId(Long pizzaId, Long ingredientId) {
        this.pizzaId = pizzaId;
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaIngredientId that = (PizzaIngredientId) o;
        return (Objects.equals(pizzaId, that.pizzaId)) &&
                (Objects.equals(ingredientId, that.ingredientId));
    }

    @Override
    public int hashCode() {
        int result = pizzaId != null ? pizzaId.hashCode() : 0;
        result = 31 * result + (ingredientId != null ? ingredientId.hashCode() : 0);
        return result;
    }
}