package com.pizzaprojet.pizzaservice.dto;

import lombok.Data;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PizzaUpdateDTO {
    private String name;
    private String description;
    private String photoUrl;

    @Positive(message = "Le prix doit être positif")
    private BigDecimal price;

    private List<Long> ingredientIds;
}