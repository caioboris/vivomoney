package com.vivomoney.controllers;

import com.vivomoney.domain.customer.Customer;
import com.vivomoney.domain.offer.Offer;
import com.vivomoney.dtos.OfferDTO;
import com.vivomoney.repositories.CustomerRepository;
import com.vivomoney.repositories.OfferRepository;
import io.micrometer.common.util.StringUtils;
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

    @PostMapping
    public ResponseEntity createOffer(@RequestBody @Validated OfferDTO data,
                                      @RequestHeader(value = "customer_document") String customerDocument) throws Exception {

        if(StringUtils.isEmpty(customerDocument)){
            throw new IllegalArgumentException("Insira um CPF válido.");
        }

        Customer customer = this.customerRepository.findCustomerByDocument(customerDocument)
                .orElseThrow(() -> new Exception("Cliente não encontrado"));

        Offer offer = new Offer(data, customer);
        offerRepository.save(offer);

        return ResponseEntity.ok(offer);
    }


}
