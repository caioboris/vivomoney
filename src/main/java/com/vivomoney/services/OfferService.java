package com.vivomoney.services;

import com.vivomoney.domain.customer.Customer;
import com.vivomoney.domain.offer.Offer;
import com.vivomoney.dtos.OfferDTO;
import com.vivomoney.repositories.CustomerRepository;
import com.vivomoney.repositories.OfferRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }

    public Offer createOffer(OfferDTO data, String customerDocument) throws IllegalArgumentException, EntityNotFoundException {

        if(StringUtils.isEmpty(customerDocument)){
            throw new IllegalArgumentException("Insira um CPF válido.");
        }
        validateOffer(data);
        Customer customer = customerRepository.findCustomerByDocument(customerDocument).orElseThrow(()-> new EntityNotFoundException());

        Offer offer = new Offer(data, customer);
        offerRepository.save(offer);

        return offer;
    }

    public List<Offer> getOffersByCustomerDocument(String document, Boolean current) throws EntityNotFoundException {
        Customer customer = customerRepository.findCustomerByDocument(document).orElseThrow(()-> new EntityNotFoundException("Cliente não encontrado"));
        List<Offer> offers = customer.getOffers();
        if(current == true){
            offers.stream().filter(x -> x.getExpirationDate()
                    .isBefore(LocalDateTime.now()))
                    .collect(Collectors.toList());
        }
        return offers;
    }

    private void validateOffer(OfferDTO offer) throws IllegalArgumentException {

        if (offer.valor() < 0) {
            throw new IllegalArgumentException("O valor deve ser maior que 0");
        }

        if (offer.numero_parcelas() < 6) {
            throw new IllegalArgumentException("Número de parcelas deve ser maior do que 6 ");
        }
    }

}
