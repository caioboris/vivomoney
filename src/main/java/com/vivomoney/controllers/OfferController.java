package com.vivomoney.controllers;

import com.vivomoney.domain.customer.Customer;
import com.vivomoney.domain.offer.Offer;
import com.vivomoney.dtos.OfferDTO;
import com.vivomoney.services.OfferService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/all")
    public ResponseEntity getAllOffers(){
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping
    public ResponseEntity getOffersByCustomerDocument(@RequestParam(value = "cpf") String document,
                                                      @RequestParam(value = "vigente", defaultValue = "false") Boolean current){

        List<Offer> offers = offerService.getOffersByCustomerDocument(document, current);
        return ResponseEntity.ok(offers);
    }

    @PostMapping
    public ResponseEntity createOffer(@RequestBody @Validated OfferDTO data,
                                      @RequestHeader(value = "customer_id") String customerDocument){

        return ResponseEntity.ok(offerService.createOffer(data,customerDocument));
    }


}
