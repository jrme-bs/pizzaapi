package com.pizzaprojet.pizzaservice.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    private String description;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(nullable = false)
    private BigDecimal prix;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PizzaIngredient> ingredients = new HashSet<>();
}