package com.vivomoney.controllers;

import com.vivomoney.domain.customer.Customer;
import com.vivomoney.dtos.CustomerDTO;
import com.vivomoney.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity getAllCustomers(){
        var allCustomers = customerRepository.findAll();
        return ResponseEntity.ok(allCustomers);
    }

    @PostMapping
    public ResponseEntity postCustomer(@RequestBody @Validated CustomerDTO data){
        Customer customer = new Customer(data);
        customerRepository.save(customer);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id){
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
