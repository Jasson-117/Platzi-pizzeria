package com.platzi.platzipizzeria.controller;

import com.platzi.platzipizzeria.persistence.entity.OrderImtemEntity;
import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;
import com.platzi.platzipizzeria.service.PizzaService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page,elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza){
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }
    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.findAllByAvailable(page,elements,sortBy,sortDirection));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }
    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByDescription(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }
    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getByDescription(@PathVariable double price){
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }

    @GetMapping("/withOut/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByNotDescription(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWithOut(ingredient));
    }
    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
    }
    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() != null && this.pizzaService.exist(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));

        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{idPizza}")
    public ResponseEntity<?> delete(@PathVariable int idPizza){
        try {
            if (this.pizzaService.exist(idPizza)) {
                this.pizzaService.delete(idPizza);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Pizza no Existe!");
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
