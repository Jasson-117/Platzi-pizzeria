package com.platzi.platzipizzeria.service;

import com.platzi.platzipizzeria.persistence.entity.OrderImtemEntity;
import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import com.platzi.platzipizzeria.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
}
