package com.pizzaprojet.pizzaservice.controller;

import com.pizzaprojet.pizzaservice.dto.PizzaCreateDTO;
import com.pizzaprojet.pizzaservice.dto.PizzaDTO;
import com.pizzaprojet.pizzaservice.dto.PizzaDetailDTO;
import com.pizzaprojet.pizzaservice.dto.PizzaUpdateDTO;
import com.pizzaprojet.pizzaservice.service.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pizzas")
@Tag(name = "pizzas", description = "Opérations liées aux pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    @Operation(summary = "Liste des pizzas", description = "Récupère la liste de toutes les pizzas disponibles")
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() {
        return ResponseEntity.ok(pizzaService.getAllPizzas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Détails d'une pizza", description = "Récupère les détails d'une pizza spécifique")
    public ResponseEntity<PizzaDetailDTO> getPizzaById(@PathVariable Long id) {
        return ResponseEntity.ok(pizzaService.getPizzaById(id));
    }

    @PostMapping
    @Operation(summary = "Ajouter pizza", description = "Ajoute une nouvelle pizza (admin seulement)",
            security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<PizzaDTO> createPizza(@Valid @RequestBody PizzaCreateDTO pizzaDTO) {
        return new ResponseEntity<>(pizzaService.createPizza(pizzaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier pizza", description = "Modifie une pizza existante (admin seulement)",
            security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<PizzaDTO> updatePizza(@PathVariable Long id, @Valid @RequestBody PizzaUpdateDTO pizzaDTO) {
        return ResponseEntity.ok(pizzaService.updatePizza(id, pizzaDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer pizza", description = "Supprime une pizza existante (admin seulement)",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Pizza supprimée avec succès")
    public ResponseEntity<Void> deletePizza(@PathVariable Long id) {
        pizzaService.deletePizza(id);
        return ResponseEntity.noContent().build();
    }
}