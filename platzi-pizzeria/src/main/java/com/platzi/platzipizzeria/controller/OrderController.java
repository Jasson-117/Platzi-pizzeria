package com.platzi.platzipizzeria.controller;

import com.platzi.platzipizzeria.persistence.entity.OrderEntity;
import com.platzi.platzipizzeria.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getAllTodayOrders(){
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }
    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getAllOutsideOrders(){
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getAllCustomerOrders(@PathVariable String id){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }
}
