package com.pizzaprojet.pizzaservice.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PizzaDTO {
    private Long id;
    private String name;
    private String description;
    private String photoUrl;
    private BigDecimal price;
    private List<Long> ingredientIds;
}