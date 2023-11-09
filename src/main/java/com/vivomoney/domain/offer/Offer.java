package com.vivomoney.domain.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vivomoney.domain.customer.Customer;
import com.vivomoney.dtos.OfferDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity(name = "offers")
@Table(name = "tb_offers")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal tax;

    private Long amount;

    private Long installments;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private Customer customer;

    private LocalDateTime expirationDate;

    public Offer(OfferDTO offerDTO, Customer customer){
        this.tax = new BigDecimal(offerDTO.taxa_mensal()).setScale(2, RoundingMode.HALF_UP);
        this.amount = offerDTO.valor();
        this.installments = offerDTO.numero_parcelas();
        this.expirationDate = LocalDateTime.now().plusDays(30);
        this.customer = customer;
    }
}
