package com.pizzaprojet.pizzaservice.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PizzaCreateDTO {
    @NotBlank(message = "Le nom est obligatoire")
    private String name;
    private String description;
    private String photoUrl;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private BigDecimal price;

    private List<Long> ingredientIds;
}