package com.pizzaprojet.pizzaservice.service;

import com.pizzaprojet.pizzaservice.dto.*;
import com.pizzaprojet.pizzaservice.exception.ResourceNotFoundException;
import com.pizzaprojet.pizzaservice.model.Pizza;
import com.pizzaprojet.pizzaservice.model.PizzaIngredient;
import com.pizzaprojet.pizzaservice.repository.PizzaIngredientRepository;
import com.pizzaprojet.pizzaservice.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaIngredientRepository pizzaIngredientRepository;

    public PizzaService(PizzaRepository pizzaRepository, PizzaIngredientRepository pizzaIngredientRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaIngredientRepository = pizzaIngredientRepository;
    }

    @Transactional(readOnly = true)
    public List<PizzaDTO> getAllPizzas() {
        return pizzaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PizzaDetailDTO getPizzaById(Long id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza non trouvée avec l'ID: " + id));
        return convertToDetailDTO(pizza);
    }

    public PizzaDTO createPizza(PizzaCreateDTO createDTO) {
        Pizza pizza = new Pizza();
        pizza.setNom(createDTO.getName());
        pizza.setDescription(createDTO.getDescription());
        pizza.setPhotoUrl(createDTO.getPhotoUrl());
        pizza.setPrix(createDTO.getPrice());

        Pizza savedPizza = pizzaRepository.save(pizza);

        if (createDTO.getIngredientIds() != null && !createDTO.getIngredientIds().isEmpty()) {
            for (Long ingredientId : createDTO.getIngredientIds()) {
                savedPizza.getIngredients().add(new PizzaIngredient(savedPizza, ingredientId));
            }
            savedPizza = pizzaRepository.save(savedPizza);
        }

        return convertToDTO(savedPizza);
    }

    public PizzaDTO updatePizza(Long id, PizzaUpdateDTO updateDTO) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza non trouvée avec l'ID: " + id));

        if (updateDTO.getName() != null) pizza.setNom(updateDTO.getName());
        if (updateDTO.getDescription() != null) pizza.setDescription(updateDTO.getDescription());
        if (updateDTO.getPhotoUrl() != null) pizza.setPhotoUrl(updateDTO.getPhotoUrl());
        if (updateDTO.getPrice() != null) pizza.setPrix(updateDTO.getPrice());

        Pizza updatedPizza = pizzaRepository.save(pizza);

        if (updateDTO.getIngredientIds() != null) {
            pizzaIngredientRepository.deleteByPizzaId(id);
            updatedPizza.getIngredients().clear();

            for (Long ingredientId : updateDTO.getIngredientIds()) {
                updatedPizza.getIngredients().add(new PizzaIngredient(updatedPizza, ingredientId));
            }
            updatedPizza = pizzaRepository.save(updatedPizza);
        }

        return convertToDTO(updatedPizza);
    }

    public void deletePizza(Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pizza non trouvée avec l'ID: " + id);
        }
        pizzaRepository.deleteById(id);
    }

    private PizzaDTO convertToDTO(Pizza pizza) {
        PizzaDTO dto = new PizzaDTO();
        dto.setId(pizza.getId());
        dto.setName(pizza.getNom());
        dto.setDescription(pizza.getDescription());
        dto.setPhotoUrl(pizza.getPhotoUrl());
        dto.setPrice(pizza.getPrix());
        return dto;
    }

    private PizzaDetailDTO convertToDetailDTO(Pizza pizza) {
        PizzaDetailDTO dto = new PizzaDetailDTO();
        dto.setId(pizza.getId());
        dto.setName(pizza.getNom());
        dto.setDescription(pizza.getDescription());
        dto.setPhotoUrl(pizza.getPhotoUrl());
        dto.setPrice(pizza.getPrix());

        List<Long> ingredientIds = pizzaIngredientRepository.findByPizzaId(pizza.getId())
                .stream()
                .map(PizzaIngredient::getIngredientId)
                .collect(Collectors.toList());

        dto.setIngredientIds(ingredientIds);

        return dto;
    }
}