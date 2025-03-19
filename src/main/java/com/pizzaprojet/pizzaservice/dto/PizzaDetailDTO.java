package com.pizzaprojet.pizzaservice.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PizzaDetailDTO {
    private Long id;
    private String nom;
    private String description;
    private String photoUrl;
    private BigDecimal prix;
    private List<Long> ingredientIds;
}