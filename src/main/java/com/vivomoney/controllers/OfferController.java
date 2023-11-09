package com.vivomoney.controllers;

import com.vivomoney.domain.customer.Customer;
import com.vivomoney.domain.offer.Offer;
import com.vivomoney.dtos.OfferDTO;
import com.vivomoney.repositories.CustomerRepository;
import com.vivomoney.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity getAllOffers(){
        var allOffers = offerRepository.findAll();
        return ResponseEntity.ok(allOffers);
    }

    public ResponseEntity createOffer(@RequestBody @Validated OfferDTO data, @RequestHeader @Validated String customerDocument) throws Exception {
        Customer customer = this.customerRepository.findCustomerByDocument(customerDocument).get();
        Offer offer = new Offer(data);
        offer.setCustomer(customer);
        offerRepository.save(offer);

        return ResponseEntity.ok(offer);
    }


}
