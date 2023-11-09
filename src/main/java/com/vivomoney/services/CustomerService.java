package com.vivomoney.services;

import com.vivomoney.domain.customer.Customer;
import com.vivomoney.dtos.CustomerDTO;
import com.vivomoney.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }

    public Customer createCustomer(@Validated CustomerDTO data){
        try {
            Customer customer = new Customer(data);
            customerRepository.save(customer);
            return customer;

        }catch(IllegalArgumentException ex){
            throw new IllegalArgumentException(ex);
        }

    }

    public void deleteCustomer(Long id){
        try{
            customerRepository.deleteById(id);

        }catch(EntityNotFoundException ex){
            throw new EntityNotFoundException(ex);
        }

    }

}
