package com.vivomoney.services;

import com.vivomoney.domain.offer.Offer;
import com.vivomoney.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OfferService {

    @Autowired
    private OfferRepository repository;
    public void validateOffer(Offer offer) throws Exception {

        if(offer.getTax().compareTo(new BigDecimal(100.00)) == 1 || offer.getTax().compareTo(new BigDecimal(0.00)) == 0){
            throw new Exception("Taxa mensal inválida");
        }

        if(offer.getAmount() < 0){
            throw new Exception("Valor inválido. O valor deve ser maior que 0");
        }

        if(offer.getInstallments() < 6){
            throw new Exception("Número de parcelas deve ser maior ");
        }
    }


    public void saveOffer(Offer offer) throws Exception {
        validateOffer(offer);
        this.repository.save(offer);
    }

}
