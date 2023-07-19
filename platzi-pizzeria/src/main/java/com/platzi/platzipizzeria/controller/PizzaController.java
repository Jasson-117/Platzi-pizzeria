package com.platzi.platzipizzeria.controller;

import com.platzi.platzipizzeria.persistence.entity.OrderImtemEntity;
import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import com.platzi.platzipizzeria.service.PizzaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }
}
