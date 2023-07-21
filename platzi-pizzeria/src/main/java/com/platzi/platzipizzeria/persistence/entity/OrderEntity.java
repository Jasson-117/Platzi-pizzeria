package com.platzi.platzipizzeria.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pizza_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order",nullable = false)
    private Integer id_order;
    @Column(name = "id_customer",nullable = false,length = 15)
    private String idCustomer;
    @Column(nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime date;
    @Column(nullable = false,columnDefinition = "DECIMAL(6,2)")
    private double total;
    @Column(nullable = false,columnDefinition = "CHAR(1)")
    private String method;
    @Column(name = "adicional_notes",length = 200)
    private String adicionalNotes;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer",referencedColumnName = "id_customer",updatable = false,insertable = false)
    @JsonIgnore
    private CustomerEntity customer ;
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    private List<OrderImtemEntity> items;

}
