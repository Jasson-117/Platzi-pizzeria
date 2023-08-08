package com.platzi.platzipizzeria.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pizza")
public class PizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza",nullable = false)
    private Integer idPizza;
    @Column(nullable = false,length = 30,unique = true)
    private String name;
    @Column(nullable = false,length = 150)
    private String descripcion;
    @Column(nullable = false,columnDefinition = "Decimal(5,2)")
    private Double price;
    @Column(columnDefinition = "TINYINT")
    private boolean vegetarian;
    @Column(columnDefinition = "TINYINT")
    private boolean vegan;
    @Column(columnDefinition = "TINYINT",nullable = false)
    private boolean available;
}
