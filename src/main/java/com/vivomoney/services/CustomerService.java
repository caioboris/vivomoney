package com.vivomoney.services;

import com.vivomoney.domain.customer.Customer;
import com.vivomoney.dtos.CustomerDTO;
import com.vivomoney.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void createCustomer(CustomerDTO customer){

    }

    public Customer findCustomerByDocument(String document){

        return new Customer();
    }
}
