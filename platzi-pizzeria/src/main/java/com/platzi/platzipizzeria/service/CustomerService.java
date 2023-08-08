package com.platzi.platzipizzeria.service;

import com.platzi.platzipizzeria.persistence.entity.CustomerEntity;
import com.platzi.platzipizzeria.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public CustomerEntity findByPhone(String phone){
        return this.customerRepository.findByPhone(phone);
    }
}
