package com.platzi.platzipizzeria.service;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import com.platzi.platzipizzeria.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
    public List<PizzaEntity> getAll(){
        return this.pizzaRepository.findAll();
    }
    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }
    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }
    public boolean exist(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
    public void delete(int idPizza){
         this.pizzaRepository.deleteById(idPizza);
    }

    public List<PizzaEntity> findAllByAvailab() {
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }
    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndAvailableIgnoreCase(name);
    }
}
