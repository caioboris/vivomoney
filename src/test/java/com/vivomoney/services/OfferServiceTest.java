package com.vivomoney.services;

import com.vivomoney.domain.customer.Customer;
import com.vivomoney.domain.offer.Offer;
import com.vivomoney.dtos.CustomerDTO;
import com.vivomoney.dtos.OfferDTO;
import com.vivomoney.repositories.CustomerRepository;
import com.vivomoney.repositories.OfferRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@ActiveProfiles("test")
class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Autowired
    @InjectMocks
    private OfferService offerService;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOfferSuccess() {
        //Creating a customer before create an offer
        String document = "12331241322";
        Customer customer = new Customer(new CustomerDTO(document, "Caio Boris", LocalDate.of(2003,1,24)));
        this.entityManager.persist(customer);
        OfferDTO offer = new OfferDTO(7.85, 10000L, 18L);

        when(customerRepository.findCustomerByDocument(document)).thenReturn(Optional.of(customer));
        offerService.createOffer(offer, document);

        verify(offerService, times(1)).createOffer(any(OfferDTO.class), eq(document));
    }

    @Test
    void createOfferFailed() {
        String document = "12331241322";
        OfferDTO offer = new OfferDTO(7.85, 10000L, 18L);

        when(customerRepository.findCustomerByDocument(document)).thenReturn(Optional.of(new Customer()));

        EntityNotFoundException notFound = Assertions.assertThrows(EntityNotFoundException.class, () ->{
            customerRepository.findCustomerByDocument(document);
        });

        verify(customerRepository, times(1)).findCustomerByDocument(eq(document));
    }

    @Test
    void getOffersByCustomerDocumentSuccess() {
        String document = "12331241322";
        Boolean current = true;

        Customer customer = new Customer(new CustomerDTO(document, "Caio Boris", LocalDate.of(2003, 1, 24)));
        when(customerRepository.findCustomerByDocument(document)).thenReturn(Optional.of(customer));

        List<Offer> offers = customer.getOffers();
        List<Offer> currentOffers = customer.getOffers()
                .stream().filter(x -> x.getExpirationDate()
                        .isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());

        when(customer.getOffers()).thenReturn(offers);

        List<Offer> result = offerService.getOffersByCustomerDocument(document, current);
        verify(customerRepository, times(1)).findCustomerByDocument(eq(document));

        if (current) {
            assertEquals(1, result.size());
            assertEquals(currentOffers, result.get(0));
        } else {
            assertEquals(offers, result);
        }
    }

}