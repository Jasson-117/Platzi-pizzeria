package com.platzi.platzipizzeria.service;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import com.platzi.platzipizzeria.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }
    public Page<PizzaEntity> getAll(int page, int elements){
        Pageable pageable = PageRequest.of(page,elements);
        return this.pizzaPagSortRepository.findAll(pageable);
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
//Con este metodo podremos paginar nuestras consutlas y filtrar por nombre de una columna
    public Page<PizzaEntity> findAllByAvailable(int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable = PageRequest.of(page,elements, sort);

        return this.pizzaPagSortRepository.findByAvailableTrue(pageable);
    }
    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findTopByAvailableTrueAndNameIgnoreCase(name).orElseThrow(()->new RuntimeException("La pizza no existe"));
    }
    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescripcionContainingIgnoreCase(ingredient);
    }
    public List<PizzaEntity> getWithOut(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescripcionNotContainingIgnoreCase(ingredient);
    }
    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }
}
