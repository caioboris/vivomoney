package com.vivomoney.repositories;

import com.vivomoney.domain.customer.Customer;
import com.vivomoney.dtos.CustomerDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Deve obter o usuário da tabela com sucesso.")
    void findCustomerByDocumentSuccess() {
        String document = "12331241322";
        CustomerDTO data = new CustomerDTO(document, "Caio Boris", LocalDate.of(2003,1,24));
        this.createCustomer(data);
        Optional<Customer> customerFounded = this.customerRepository.findCustomerByDocument(document);

        assertThat(customerFounded.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Não deve obter o usuário da tabela.")
    void findCustomerByDocumentFailed() {
        String document = "12331241322";
        Optional<Customer> customerFounded = this.customerRepository.findCustomerByDocument(document);

        assertThat(customerFounded.isEmpty()).isTrue();
    }

    private Customer createCustomer(CustomerDTO data){
        Customer customer = new Customer(data);
        this.entityManager.persist(customer);
        return customer;
    }
}