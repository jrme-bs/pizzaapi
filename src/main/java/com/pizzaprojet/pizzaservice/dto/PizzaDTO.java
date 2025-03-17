package com.pizzaprojet.pizzaservice.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PizzaDTO {
    private Long id;
    private String name;
    private String description;
    private String photoUrl;
    private BigDecimal price;
}