package com.vivomoney.repositories;

import com.vivomoney.domain.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<List<Offer>> findOfferByCustomerDocument(String document);

}
